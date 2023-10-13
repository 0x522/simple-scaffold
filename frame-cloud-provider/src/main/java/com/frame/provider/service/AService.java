package com.frame.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frame.provider.model.entity.A;

import java.util.List;


public interface AService extends IService<A> {

    List<Object> callService(List<Long> ids);
}
