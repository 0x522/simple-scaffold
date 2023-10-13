package com.frame.util;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: chenyuntao
 **/
public class TimeStampUtils {

    public static String DEFAULT_PATTERN_DATE = "yyyyMMddHHmmss";

    /**
     * 时间戳
     *
     * @return
     */
    public static String generateTimeStamp() {
        return DateUtil.date()
                .toString()
                .replace("-", "")
                .replace(" ", "")
                .replace(":", "");
    }

    /**
     * 时间戳-毫秒部分
     *
     * @return
     */
    public static String generateTimeStampMillis() {
        return String.valueOf(DateUtil.millisecond(new Date()));
    }

    /**
     * 日期差
     *
     * @param sDate 计划日期
     * @param nDate 实际日期
     */
    public static long getDateDif(String sDate, String nDate) throws ParseException {
        return DateUtil.between(toDate(sDate), toDate(nDate), DateUnit.MINUTE, false);
    }

    public static Date toDate(String dateString) throws ParseException {
        return (new SimpleDateFormat(DEFAULT_PATTERN_DATE)).parse(dateString);
    }

}
