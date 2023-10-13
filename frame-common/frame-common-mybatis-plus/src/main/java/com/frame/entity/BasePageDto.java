package com.frame.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenyuntao
 */
@Data
public class BasePageDto implements Serializable {

    private Long current;

    private Long size;

}
