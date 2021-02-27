package org.youyuan.jwt.utils.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/2/24 20:40
 */
@Data
@Builder
public class PageResponse<T> {
    private int total;
    private List<T> list;
}
