package com.frame.provider.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.frame.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * custom_type
 *
 * @author Qiruilong
 */
@Data

@TableName("custom_type")
@Accessors(chain = true)
@ApiModel(value = "custom_type对象", description = "值集类型")
public class CustomType extends BaseEntity {
    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 值集类型
     */
    @ApiModelProperty(value = "值集类型")
    private String typeCode;

    /**
     * 值集名称
     */
    @ApiModelProperty(value = "值集名称")
    private String typeName;

    private static final long serialVersionUID = 1L;
}
