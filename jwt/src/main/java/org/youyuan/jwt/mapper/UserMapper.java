package org.youyuan.jwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.youyuan.jwt.domain.UserPO;
import org.youyuan.jwt.vo.response.UserInfo;
import org.youyuan.jwt.vo.response.UserVO;

import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/1/30 21:50
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

    /**
     * 插入记录（回显主键）
     * @param userPO
     * @return
     */
    int insertGeneratedPrimaryKey(UserPO userPO);

    /**
     * 插入用户角色关系
     *
     * @param userId
     * @param roleId
     */
    void insertUserRoleRel(@Param(value = "userId") Integer userId,@Param(value = "roleId") Integer roleId);

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
     * @param page
     * @param size
     * @param search
     * @return
     */
    List<UserVO> getUserInfoList(@Param(value = "page") int page, @Param(value = "size") int size,@Param("search") String search);






}
