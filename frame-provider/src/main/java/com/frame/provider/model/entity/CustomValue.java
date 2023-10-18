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
 * custom_value
 *
 * @author Qiruilong
 */
@Data

@TableName("custom_value")
@Accessors(chain = true)
@ApiModel(value = "custom_value对象", description = "值集表")
public class CustomValue extends BaseEntity {
    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 值集类型ID
     */
    @ApiModelProperty(value = "值集类型ID")
    private Long typeId;

    /**
     * 值集类型编码
     */
    @ApiModelProperty(value = "值集类型编码")
    private String typeCode;

    /**
     * 值集编码
     */
    @ApiModelProperty(value = "值集编码")
    private String valueCode;

    /**
     * 值集名称
     */
    @ApiModelProperty(value = "值集名称")
    private String valueName;

    /**
     * 值集描述
     */
    @ApiModelProperty(value = "值集描述")
    private String valueDesc;

    private static final long serialVersionUID = 1L;
}
