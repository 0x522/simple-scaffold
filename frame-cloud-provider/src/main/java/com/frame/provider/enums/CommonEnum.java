package com.frame.provider.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: 谢浩冬
 * @Date: 2023/9/4 15:17
 * @Version: V1.0
 */
public enum CommonEnum {

    /**
     * 审批状态-未提交
     */
    YES("是", "1"),
    /**
     * 审批状态-未提交
     */
    NO("否", "0");



    private final String name;
    private final String code;

    CommonEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    /**
     * 获取状态
     *
     * @return
     */
    public Map<String, String> getAllCommonEnum() {
        Map<String, String> res = new HashMap<>();
        for (CommonEnum value : values()) {
            res.put(value.name, value.code);
        }
        return res;
    }
}
