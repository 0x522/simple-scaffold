package com.frame.provider.util;


import cn.hutool.core.util.RandomUtil;
import com.frame.util.TimeStampUtils;

/**
 * 编号生成器
 *
 * @author: chenyuntao
 **/
public class CustomNumGenerator extends TimeStampUtils {

    /**
     * 工单编号-基于时间戳生成
     *
     * @return
     */
    public static String generateWorkOrderNo() {
        //随机数+时间戳(毫秒)策略
        return "N" + RandomUtil.randomNumbers(4) + generateTimeStamp().substring(8) + generateTimeStampMillis();
    }
}
