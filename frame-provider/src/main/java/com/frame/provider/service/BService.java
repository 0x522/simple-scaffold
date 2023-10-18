package com.frame.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frame.provider.model.entity.B;

import java.util.List;

public interface BService extends IService<B> {

    List<Object> callService(List<Long> ids);
}
