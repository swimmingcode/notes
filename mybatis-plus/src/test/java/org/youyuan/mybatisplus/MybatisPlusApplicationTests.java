package org.youyuan.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.youyuan.mybatisplus.domain.user.po.UserPO;
import org.youyuan.mybatisplus.mapper.UserMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
class MybatisPlusApplicationTests {

    @Autowired
    UserMapper userMapper;

    /**
     * 常用SQL符号
     */
    @Test
    void contextLoads() {
        QueryWrapper<UserPO> objectQueryWrapper = new QueryWrapper<>();
        //1、hashMap
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", "root");
        hashMap.put("age", 20);
        objectQueryWrapper.allEq(hashMap);

        //2、ge
        objectQueryWrapper.ge("age", 21);

        //3、gt
        objectQueryWrapper.gt("age", 2);

        //4、ne
        objectQueryWrapper.ne("name", "root");

        //5、ge
        objectQueryWrapper.ge("age", 2);

        //6、like
        objectQueryWrapper.like("name", "roo");

        //7、like %小
        objectQueryWrapper.likeLeft("name", "小");

        //8、 like '小%'
        objectQueryWrapper.likeRight("name", "小");

        // 9、inSQL
        objectQueryWrapper.inSql("id", "select id from user where id < 10");
        objectQueryWrapper.inSql("age", "select age from user where age > 3");
        objectQueryWrapper.orderByDesc("age");
        objectQueryWrapper.orderByAsc("age");
        objectQueryWrapper.having("id > 8");

        userMapper.selectList(objectQueryWrapper).forEach(System.out::println);
    }

    /**
     * 分页查询
     */
    @Test
    public void test() {
        //通过Id查询
        log.info("{}", userMapper.selectById(1));
        //批量查询
        log.info("{}", userMapper.selectBatchIds(Arrays.asList(1, 2, 3)));
        // Map 只能做等值判断，逻辑判断需要使用 Wrapper 来处理
        Map<String, Object> map = new HashMap<>();
        map.put("id", 7);
        log.info("{}", userMapper.selectByMap(map));

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", 7);
        log.info("{}", userMapper.selectCount(wrapper));

        //将查询的结果集封装到Map中
        log.info("{}", userMapper.selectMaps(wrapper));
        //将查询的结果集封装到List中
        log.info("{}", userMapper.selectList(wrapper));


        // 分页查询
        Page<UserPO> page = new Page<>(2, 2);
        Page<UserPO> result = userMapper.selectPage(page, null);
        log.info("{}", result.getSize());
        log.info("{}", result.getTotal());
        log.info("{}", result.getRecords().toString());

        QueryWrapper wrapperTemp = new QueryWrapper();
        Page<Map<String, Object>> pageTemp = new Page<>(1, 2);
        userMapper.selectMapsPage(pageTemp, null).getRecords().forEach(System.out::println);
        userMapper.selectObjs(null).forEach(System.out::println);
        System.out.println(userMapper.selectOne(wrapperTemp));
    }

    /**
     * 添加
     */
    @Test
    public void testTwo() {
        //添加
        UserPO user = new UserPO();
        user.setTitle("小明");
        user.setAge(22);
        userMapper.insert(user);
    }

    /**
     * 删除
     */
    @Test
    public void testThree() {
        userMapper.deleteById(1);
        userMapper.deleteBatchIds(Arrays.asList(7, 8));
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("age", 14);
        userMapper.delete(wrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("id", 10);
        userMapper.deleteByMap(map);
    }

    @Test
    public void TestFour() {
        UserPO user = userMapper.selectById(1);
        user.setTitle("小红");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("age",20);
        userMapper.update(user,wrapper);
    }


}
