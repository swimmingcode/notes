package com.cico.virmachine.infrastructure.common;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describe:分页结果类
 * @Author: youjiancheng
 * @date 2021/4/20 9:28
 */
@Data
@Builder
public class PageResponse<T> {
    private Long total;
    private List<T> list;
}
