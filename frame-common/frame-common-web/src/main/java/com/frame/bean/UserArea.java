package com.frame.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: chenyuntao
 **/
@Data
public class UserArea implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 国家
     */
    private String country;

    /**
     * 地区
     */
    private String region;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

}
