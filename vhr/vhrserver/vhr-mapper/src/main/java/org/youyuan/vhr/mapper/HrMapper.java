package org.youyuan.vhr.mapper;

import org.apache.ibatis.annotations.Param;

import org.youyuan.vhr.bean.Role;
import org.youyuan.vhr.bean.Hr;

import java.util.List;


public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    Hr selectByPrimaryKey(Integer id);

    List<Hr> selectAll();

    int updateByPrimaryKey(Hr record);

    /**
     * 根据Hr的username查询Hr
     */
    Hr loadHrByUsername(@Param("username") String s);

    /**
     * 根据Hr的id获取hr所拥有的所有角色
     */
    List<Role> getHrRoleById(Integer id);

    /**
     * 通过Hid获取Hr
     **/
    List<Hr> getHrByHid(@Param("id") Integer id, @Param("keywords") String keywords);
}