package com.frame.provider.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.provider.mapper.CMapper;
import com.frame.provider.model.entity.C;
import com.frame.provider.service.CService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class CServiceImpl extends ServiceImpl<CMapper, C> implements CService {
    @Override
    public List<Object> callService(List<Long> ids) {
        if (CollectionUtil.isNotEmpty(ids)) {
            return Collections.singletonList("访问到C service");
        }
        return null;
    }
}

