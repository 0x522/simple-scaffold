package com.frame.provider.controller;

import com.frame.common.base.Result;
import com.frame.provider.model.vo.CustomListVo;
import com.frame.provider.service.ICustomValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "值集")
@RestController
@RequestMapping("/provider/customValue")
public class CustomValueController {

    @Autowired
    private ICustomValueService customValueService;

    /**
     * 根据code查询值集列表
     *
     * @param typeCode
     * @return
     */
    @GetMapping(value = "/queryCustomListByCode")
    @ApiOperation(value = "根据code查询值集列表", notes = "根据code查询值集列表")
    public Result<List<CustomListVo>> queryCustomListByCode(@RequestParam String typeCode) {
        return Result.ok(customValueService.queryCustomListByCode(typeCode));
    }

    /**
     * 根据code查询值集详情
     *
     * @param typeCode
     * @return
     */
    @GetMapping(value = "/queryCustomValueByCode")
    @ApiOperation(value = "根据code查询值集", notes = "根据code查询值集")
    public Result<CustomListVo> queryCustomValueByCode(@RequestParam String typeCode,
                                                       @RequestParam String valueCode) {
        return Result.ok(customValueService.queryCustomValueByCode(typeCode, valueCode));
    }
}
