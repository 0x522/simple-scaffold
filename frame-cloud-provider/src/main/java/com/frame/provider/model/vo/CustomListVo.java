package com.frame.provider.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CustomListVo implements Serializable {
    private static final long serialVersionUID = 425104188234233762L;
    /**
     * 主键id
     */
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
}
