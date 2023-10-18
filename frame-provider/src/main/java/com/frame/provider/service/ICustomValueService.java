package com.frame.provider.service;

import com.frame.provider.model.vo.CustomListVo;

import java.util.List;

/**
 * @author Admin
 */
public interface ICustomValueService {

    /**
     * 查询全部值集code
     *
     * @return
     */
    List<String> queryCustomTypeCodeList();

    /**
     * 根据type查询值集
     * @param typeCodes
     * @return
     */
    List<CustomListVo> queryAllCustomType(List<String> typeCodes);
    /**
     * 根据code查询值集列表
     *
     * @param typeCode
     * @return
     */
    List<CustomListVo> queryCustomListByCode(String typeCode);

    /**
     * 根据code查询值集
     *
     * @param typeCode
     * @param valueCode
     * @return
     */
    CustomListVo queryCustomValueByCode(String typeCode, String valueCode);
}
