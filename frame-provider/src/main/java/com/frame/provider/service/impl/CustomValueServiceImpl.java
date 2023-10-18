package com.frame.provider.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.provider.mapper.CustomTypeMapper;
import com.frame.provider.mapper.CustomValueMapper;
import com.frame.provider.model.entity.CustomType;
import com.frame.provider.model.entity.CustomValue;
import com.frame.provider.model.vo.CustomListVo;
import com.frame.provider.service.ICustomValueService;
import com.frame.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: qiruilong
 **/
@Service
public class CustomValueServiceImpl extends ServiceImpl<CustomValueMapper, CustomValue> implements ICustomValueService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CustomTypeMapper customTypeMapper;

    @Override
    public List<String> queryCustomTypeCodeList() {
        QueryWrapper<CustomType> qw = new QueryWrapper<>();
        qw.select("distinct type_code");
        List<CustomType> customTypes = customTypeMapper.selectList(qw);
        if (CollectionUtil.isNotEmpty(customTypes)) {
            return customTypes.stream().map(CustomType::getTypeCode).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<CustomListVo> queryAllCustomType(List<String> typeCodes) {
        if (CollectionUtil.isNotEmpty(typeCodes)) {
            QueryWrapper<CustomValue> qw = new QueryWrapper<>();
            qw.in("type_code", typeCodes);
            qw.orderByDesc("type_code");
            List<CustomValue> customValues = baseMapper.selectList(qw);
            if (CollectionUtil.isNotEmpty(customValues)) {
                return BeanUtil.copyToList(customValues, CustomListVo.class);
            }
        }
        return null;
    }

    /**
     * 根据code查询值集列表
     *
     * @param typeCode
     * @return
     */
    @Override
    public List<CustomListVo> queryCustomListByCode(String typeCode) {
        List<CustomListVo> res = new ArrayList<>();
        String keyPattern = redisUtil.buildKeyBySeparator(typeCode, "*");
        Set<String> keys = redisUtil.getKeys(1, keyPattern);
        for (String key : keys) {
            String customValue = redisUtil.get(1, key);
            CustomListVo customListVo = JSON.parseObject(customValue, CustomListVo.class);
            if (ObjectUtil.isNotEmpty(customListVo)) {
                res.add(customListVo);
            }
        }
        if (CollectionUtil.isNotEmpty(res)) {
            return res.stream().sorted(Comparator.comparing(CustomListVo::getValueCode)).collect(Collectors.toList());
        }
        LambdaQueryWrapper<CustomValue> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotEmpty(typeCode), CustomValue::getTypeCode, typeCode);
        List<CustomValue> list = this.list(queryWrapper);
        res = BeanUtil.copyToList(list, CustomListVo.class);
        if (CollectionUtil.isNotEmpty(res)) {
            for (CustomListVo custom : res) {
                String key = redisUtil.buildKeyBySeparator(custom.getTypeCode(), custom.getValueCode());
                redisUtil.set(1, key, JSONObject.toJSONString(custom));
            }
        }
        return res.stream().sorted(Comparator.comparing(CustomListVo::getValueCode)).collect(Collectors.toList());
    }

    /**
     * 根据code查询值集
     *
     * @param typeCode
     * @param valueCode
     * @return
     */
    @Override
    public CustomListVo queryCustomValueByCode(String typeCode, String valueCode) {
        String key = redisUtil.buildKeyBySeparator(typeCode, valueCode);
        if (redisUtil.exists(1, key)) {
            String value = redisUtil.get(1, key);
            return JSON.parseObject(value, CustomListVo.class);
        }
        LambdaQueryWrapper<CustomValue> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotEmpty(typeCode), CustomValue::getTypeCode, typeCode);
        queryWrapper.eq(ObjectUtil.isNotEmpty(valueCode), CustomValue::getValueCode, valueCode);
        CustomValue customValue = this.getOne(queryWrapper);
        CustomListVo customListVo = null;
        if (customValue != null) {
            customListVo = BeanUtil.copyProperties(customValue, CustomListVo.class);
            redisUtil.set(1, key, JSONObject.toJSONString(customListVo));
        }
        return customListVo;
    }
}
