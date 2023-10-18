package com.frame.provider.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.provider.mapper.BMapper;
import com.frame.provider.model.entity.B;
import com.frame.provider.service.BService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BServiceImpl extends ServiceImpl<BMapper, B> implements BService {

    @Override
    public List<Object> callService(List<Long> ids) {
        if (CollectionUtil.isNotEmpty(ids)) {
            return Collections.singletonList("访问到B service");
        }
        return null;
    }
}
