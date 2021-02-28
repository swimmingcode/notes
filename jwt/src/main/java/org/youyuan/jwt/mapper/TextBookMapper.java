package org.youyuan.jwt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.youyuan.jwt.domain.TextBookPO;
import org.youyuan.jwt.vo.response.TextBookVO;

import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/2/27 15:40
 */
public interface TextBookMapper extends BaseMapper<TextBookPO> {
    /**
     * 教材列表
     *
     * @param i
     * @param size
     * @param search
     * @return
     */
    List<TextBookVO> getTextBookList(@Param(value = "page") int page, @Param(value = "size") int size, @Param("search") String search);
}
