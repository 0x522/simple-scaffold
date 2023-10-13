package com.frame.util;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ioc util
 *
 * @author chenyuntao
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * get bean from ioc
     *
     * @param type
     * @return
     */
    public static Object getBean(Class type) {
        return applicationContext.getBean(type);
    }

    /**
     * get original object by agent object
     *
     * @param candidate
     * @return
     */
    public static Object getOriAgentBean(Object candidate) {
        return AopProxyUtils.getSingletonTarget(candidate);
    }
}
