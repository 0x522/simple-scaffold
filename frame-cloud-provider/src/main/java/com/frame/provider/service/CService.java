package com.frame.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frame.provider.model.entity.C;

import java.util.List;

public interface CService extends IService<C> {
    List<Object> callService(List<Long> ids);
}

