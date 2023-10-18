package com.frame.provider.controller;


import com.frame.common.base.Result;
import com.frame.provider.service.CService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/provider/C")
@Slf4j
public class CController {

    @Autowired
    private CService cService;

    @GetMapping(value = "/callService")
    public Result<List<Object>> callService(@RequestParam List<Long> ids) {
        List<Object> objects = cService.callService(ids);
        return Result.ok(objects);
    }
}

