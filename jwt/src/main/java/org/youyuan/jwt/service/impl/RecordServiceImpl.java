package org.youyuan.jwt.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youyuan.jwt.domain.RecordPO;
import org.youyuan.jwt.domain.TextBookPO;
import org.youyuan.jwt.mapper.RecordMapper;
import org.youyuan.jwt.mapper.TextBookMapper;
import org.youyuan.jwt.service.RecordService;
import org.youyuan.jwt.utils.common.response.ResponseCode;
import org.youyuan.jwt.utils.diyenum.RecordType;
import org.youyuan.jwt.utils.exception.ExceptionFactory;
import org.youyuan.jwt.utils.jwt.Token;
import org.youyuan.jwt.vo.request.ReserveBookVO;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/3/6 15:23
 */
@Service
@Slf4j
public class RecordServiceImpl implements RecordService {

    private final String KEY = "KEY_RECORD";

    private final Long SUCCESS = 1L;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    TextBookMapper textBookMapper;

    @Autowired
    RecordMapper recordMapper;


    @Override
    public void textBookReserve(ReserveBookVO reserveBookVO, Token token) {
        while (true) {
            //占位
            String value = UUID.randomUUID().toString();
            Boolean ok = redisTemplate.opsForValue().setIfAbsent(KEY,value, 5L, TimeUnit.SECONDS);
            if (ok) {
                Object o = redisTemplate.opsForValue().get(KEY);
                log.info("开始进入,value为",o);
//                try {
//                    TimeUnit.SECONDS.sleep(10L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                //查询原有的数量
                TextBookPO textBookPO = textBookMapper.selectById(reserveBookVO.getTextId());
                log.info("库存为：{}",textBookPO);
                Optional.ofNullable(textBookPO).orElseThrow(() -> new ExceptionFactory(ResponseCode.BOOK_NOT_EXISTS));
                if (textBookPO.getBookNumber() < reserveBookVO.getTextBookSize() || textBookPO.getBookNumber() <= 0) {
                    log.error("库存不够");
                    //释放锁 否者会发生死锁
                    Boolean unlock = unlock(KEY, value);
                    if (!unlock) {
                        log.error("解锁失败");
                    }
                    throw new ExceptionFactory(ResponseCode.BOOK_NUMBER_NOT_ENOUGH.getCode(), ResponseCode.BOOK_NUMBER_NOT_ENOUGH.getMessage()
                            + "【当前库存为：" + textBookPO.getBookNumber() + "】");
                }
                RecordPO recordPO = RecordPO.builder().build();
                //进行入库处理
                BeanUtils.copyProperties(reserveBookVO, recordPO);
                recordPO.setUserId(token.getId());
                recordPO.setUserName(token.getName());
                recordPO.setRecordType(RecordType.SCHEDULED);
                recordPO.setTextBookName(textBookPO.getTextName());

                textBookReserveUpdateDB(recordPO,textBookPO,reserveBookVO);

                TextBookPO textBookPO1 = textBookMapper.selectById(reserveBookVO.getTextId());
                log.info("==========textBookPO1={}===========",textBookPO1);
                //释放锁
                //1、判断value是否位当前线程产生并比较  2、删除
                //变成原子性
//                if (value.equals(redisTemplate.opsForValue().get(KEY))) {
//                    redisTemplate.delete(KEY);
//                }
                try {
                    Boolean unlock = unlock(KEY, value);
                    if (!unlock) {
                        log.error("解锁失败");
                    }
                } catch (Exception e) {
                    log.error("except：解锁失败");
                    e.printStackTrace();
                }
                Object o1 = redisTemplate.opsForValue().get(KEY);
                log.info("后面进入,value为",o1);
                break;
            } else {

                try {
                    TimeUnit.MICROSECONDS.sleep(1000L);
                } catch (  Exception e) {
                    e.printStackTrace();
                }
                log.debug("================排队中========================");
            }

        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void textBookReserveUpdateDB(RecordPO recordPO, TextBookPO textBookPO, ReserveBookVO reserveBookVO) {
        recordMapper.insert(recordPO);
        //减少库存
        Integer bookNumber = textBookPO.getBookNumber();
        textBookPO.setBookNumber(bookNumber - reserveBookVO.getTextBookSize());
        textBookMapper.updateById(textBookPO);
    }




    @Override
    @Transactional(rollbackFor = Exception.class)
     public void textBookUnSubscribe(ReserveBookVO reserveBookVO, Token token) {
        TextBookPO textBookPO = textBookMapper.selectById(reserveBookVO.getTextId());
        Optional.ofNullable(textBookPO).orElseThrow(() -> new ExceptionFactory(ResponseCode.BOOK_NOT_EXISTS));


        RecordPO recordPO = RecordPO.builder().build();
        //进行入库处理
        BeanUtils.copyProperties(reserveBookVO, recordPO);
        recordPO.setUserId(token.getId());
        recordPO.setUserName(token.getName());
        recordPO.setRecordType(RecordType.WITHDRAW);
        recordPO.setTextBookName(textBookPO.getTextName());
        recordMapper.insert(recordPO);

        //添加库存
        Integer bookNumber = textBookPO.getBookNumber();
        textBookPO.setBookNumber(bookNumber + reserveBookVO.getTextBookSize());
        textBookMapper.updateById(textBookPO);
    }


    /**
     * 使用lua脚本解锁
     *
     * @param key
     * @param value
     * @return
     */
    private Boolean unlock(String key, String value) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(script,Long.class);
        Object result = redisTemplate.execute(redisScript, Arrays.asList(key),value);
        if(SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

}
