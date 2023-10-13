package com.frame.provider.controller;

import com.frame.common.base.Result;
import com.frame.provider.service.BService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/provider/B")
public class BController {

    @Autowired
    private BService bService;

    @GetMapping(value = "/callService")
    public Result<List<Object>> callService(@RequestParam List<Long> ids) {
        List<Object> objects = bService.callService(ids);
        return Result.ok(objects);
    }


}
