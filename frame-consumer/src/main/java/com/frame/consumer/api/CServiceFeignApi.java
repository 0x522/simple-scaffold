package com.frame.consumer.api;


import com.frame.common.base.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Admin
 */
@Component
@FeignClient("frame-cloud-C")
public interface CServiceFeignApi {

    @GetMapping(value = "/callService")
    Result<List<Object>> callService(@RequestParam List<Long> ids);

}
