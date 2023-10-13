package com.frame.provider.cache;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.frame.provider.model.vo.CustomListVo;
import com.frame.provider.service.ICustomValueService;
import com.frame.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chenyuntao
 */
@Component
@Slf4j
public class CustomCacheInitializer implements CommandLineRunner {

    @Autowired
    private ICustomValueService customValueService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void run(String... args) {
//        Set<String> valueSet = redisUtil.getKeys(1, "*");
//        redisUtil.clear(1, valueSet);
//        Set<String> tokenSet = redisUtil.getKeys(0, "*");
//        redisUtil.clear(0, tokenSet);
        List<String> typeCodes = customValueService.queryCustomTypeCodeList();
        List<CustomListVo> customListVos = customValueService.queryAllCustomType(typeCodes);
        if (CollectionUtil.isNotEmpty(customListVos)) {
            for (CustomListVo customListVo : customListVos) {
                String key = String.join(".", customListVo.getTypeCode(), customListVo.getValueCode());
                redisUtil.set(1, key, JSONObject.toJSONString(customListVo));
            }
        }
    }
}
