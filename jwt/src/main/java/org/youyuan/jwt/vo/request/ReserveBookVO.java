package org.youyuan.jwt.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Describe: 教材实体类
 * @Author: youjiancheng
 * @date 2021/3/6 15:42
 */
@Data
public class ReserveBookVO {

    @ApiModelProperty("教材ID")
    private Integer textId;


    @ApiModelProperty("教材数量")
    private Integer textBookSize;
}
