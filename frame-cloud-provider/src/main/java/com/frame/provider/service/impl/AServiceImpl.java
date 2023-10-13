package com.frame.provider.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.provider.mapper.AMapper;
import com.frame.provider.model.entity.A;
import com.frame.provider.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class AServiceImpl extends ServiceImpl<AMapper, A> implements AService {

    @Autowired
    AMapper aMapper;

    @Override
    public List<Object> callService(List<Long> ids) {
        if (CollectionUtil.isNotEmpty(ids)) {
            return Collections.singletonList("访问到A service");
        }
        return null;
    }
}
