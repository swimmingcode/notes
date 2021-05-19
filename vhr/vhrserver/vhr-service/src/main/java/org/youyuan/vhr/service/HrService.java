package org.youyuan.vhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.youyuan.vhr.mapper.HrMapper;
import org.youyuan.vhr.bean.Hr;
import org.youyuan.vhr.utils.HrUtils;

import java.util.List;


@Service
public class HrService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    /**
     * 根据用户名判断是否有该用户
     * 如果有，查询其相应的角色，并为其roles字段赋值
     * 否者，直接抛出异常
     * 表单认证进入此方法*
     **/
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        /**
         * 根据名字查询是否有该hr
         * */
        Hr hr = hrMapper.loadHrByUsername(name);
        if (hr == null) {
            throw new UsernameNotFoundException("账户名不存在");
        }
        /**
         * 查询该hr的角色
         * 并赋值给其属性List<Role>
         * */
        hr.setRoles(hrMapper.getHrRoleById(hr.getId()));
        return hr;
    }

    /**
     * 通过Hid获取Hr
     **/
    public List<Hr> getHrByHid(String keywords) {
        return hrMapper.getHrByHid(HrUtils.getHr().getId(), keywords);
    }

    /**
     * 修改Hr状态
     **/
    public int updateHr(Hr hr) {
        return hrMapper.updateByPrimaryKey(hr);
    }

    /**
     * 删除Hr
     **/
    public int deleteHrByRid(Integer rid) {
        return hrMapper.deleteByPrimaryKey(rid);
    }
}
