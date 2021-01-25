package org.youyuan.mybatisplus.domain.user.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.youyuan.mybatisplus.domain.user.repository.po.UserPO;

/**
 * @Author youjiancheng
 * @Date 2021/1/21 17:30
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {
}
