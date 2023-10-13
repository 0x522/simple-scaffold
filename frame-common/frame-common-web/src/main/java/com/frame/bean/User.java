package com.frame.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 当前登录用户
 *
 * @author: chenyuntao
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phoneNumber;

    /**
     * 公司
     */
    private String company;
    /**
     * 部门1
     */
    private String department1;

    /**
     * 部门2
     */
    private String department2;

    /**
     * 部门3
     */
    private String department3;

    /**
     * 职位
     */
    private String position;

    /**
     * 地区
     */
    private List<UserArea> userAreas;
}
