package org.youyuan.jwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.youyuan.jwt.domain.UserPO;
import org.youyuan.jwt.vo.response.UserInfo;

import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/1/30 21:50
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    UserInfo getUserInfo(@Param("userId") Integer userId);

    /**
     * 获取用户列表
     *
     * @return
     */
    List<UserInfo> getUserList();
}
