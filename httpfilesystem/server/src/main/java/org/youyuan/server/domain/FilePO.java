package org.youyuan.server.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Describe: 文件实体类
 * @Author: youjiancheng
 * @date 2021/4/6 15:17
 */
@TableName("file")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FilePO {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文件大小
     */
    @TableField(value = "size")
    private long size;

    /**
     * 文件类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 原始文件名
     */
    @TableField(value = "old_name")
    private String oldName;

    /**
     * 新名称
     */
    @TableField(value = "new_name")
    private String newName;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 文件目录地址
     */
    @TableField(value = "file_address")
    private String fileAddress;


}
