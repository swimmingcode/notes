package org.youyuan.jwt.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.youyuan.jwt.domain.TextBookPO;
import org.youyuan.jwt.mapper.TextBookMapper;
import org.youyuan.jwt.service.TextBookService;
import org.youyuan.jwt.utils.common.DateUtils;
import org.youyuan.jwt.utils.common.web.GlobalHttpUtils;
import org.youyuan.jwt.vo.request.AddTextBookVO;
import org.youyuan.jwt.vo.response.TextBookVO;
import org.youyuan.jwt.vo.response.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/2/27 15:59
 */
@Service
@Slf4j
public class TextBookServiceImpl implements TextBookService {

    @Autowired
    TextBookMapper textBookMapper;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public String upload(MultipartFile file) {
        HttpServletRequest globalHttpRequest = GlobalHttpUtils.getGlobalHttpRequest();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        //文件名称
        String format = DateUtils.dateImageFolder(new Date());
        String oldName = file.getOriginalFilename();

        //创建文件夹
        // TODO: 2021/2/28 第一种 直接放在static下的image中
        String path = System.getProperty("user.dir") + File.separator +  "jwt" + File.separator + "src" + File.separator + "main" + File.separator + "resources"+File.separator + "static" + File.separator + "image" + format;//
        // TODO: 2021/2/28 第二种 放在webapp下面
//        String path = globalHttpRequest.getServletContext().getRealPath("/img/") + format;
        log.info(path);
        File folder = new File(path);
        if (!folder.exists()) {
            //递归 一层一层创建
            //mkdir 只有一级
            folder.mkdirs();
        }
        try {
            inputStream = file.getInputStream();
            byte[] bytes = new byte[1024];
            //文件路径
            String fileName = RandomStringUtils.randomAlphabetic(8)+ oldName.substring(oldName.lastIndexOf("."));
            outputStream  = new FileOutputStream(new File(path , fileName));
            int len;
            while ((len = inputStream.read(bytes))!= -1) {
                outputStream.write(bytes);
            }
            log.info("文件路径为=" + path + fileName);
            //第一种
            String url = globalHttpRequest. getScheme() + "://"+globalHttpRequest.getServerName() +":" + globalHttpRequest.getServerPort()+"/static/image" + format+ fileName;
            //第二种
            // TODO: 2021/2/28 有问题
//            String url= globalHttpRequest. getScheme() + "://"+globalHttpRequest.getServerName() +":" + globalHttpRequest.getServerPort()+"/img" + format +fileName;
            log.info(url);
            String str = RandomStringUtils.randomAlphabetic(5);
            redisTemplate.opsForValue().set(str,url,5, TimeUnit.MINUTES);
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void addTextBooks(AddTextBookVO addTextBookVO) {
        TextBookPO textBookPO = new TextBookPO();
        BeanUtils.copyProperties(addTextBookVO, textBookPO);
        textBookMapper.insert(textBookPO);
    }

    @Override
    public void updateTextBook(TextBookVO addTextBookVO) {
        TextBookPO textBookPO = new TextBookPO();
        BeanUtils.copyProperties(addTextBookVO, textBookPO);
        textBookMapper.updateById(textBookPO);
    }

    @Override
    public TextBookVO getTextBookById(Integer id) {
        TextBookPO textBookPO = textBookMapper.selectById(id);
        TextBookVO target = new TextBookVO();
        BeanUtils.copyProperties(textBookPO, target);
        return target;
    }

    @Override
    @Transactional
    public void deleteTextBook(Integer id) {
        textBookMapper.deleteById(id);
    }

    @Override
    public List<TextBookVO> getTextBookList(Integer page, Integer size, String search) {
        List<TextBookVO> textBookList = textBookMapper.getTextBookList((page-1) * size,size,search);
        return textBookList;
    }
}
