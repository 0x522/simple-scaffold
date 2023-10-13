package com.frame.trace;

import org.slf4j.MDC;

import java.util.UUID;

/**
 * @author: chenyuntao
 **/
public class TraceIdContext {
    public static final ThreadLocal<String> CURRENT_TRACE_ID = new InheritableThreadLocal<>();

    public static String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    public static String getCurrentTraceId() {
        return MDC.get(TraceIdConstant.TRACE_ID);
    }

    public static void setCurrentTraceId(String currentTraceId) {
        MDC.put(TraceIdConstant.TRACE_ID, currentTraceId);
    }

    public static void clearTraceId() {
        CURRENT_TRACE_ID.set(null);
        CURRENT_TRACE_ID.remove();
    }

}
