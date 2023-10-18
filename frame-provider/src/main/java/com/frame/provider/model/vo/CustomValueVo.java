package com.frame.provider.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.frame.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data

@TableName("custom_value")
@Accessors(chain = true)
@ApiModel(value = "custom_value对象", description = "值集表")
public class CustomValueVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

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

}
