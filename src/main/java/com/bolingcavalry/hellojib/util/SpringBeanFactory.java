package com.bolingcavalry.hellojib.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author liaohua1
 * @date 2020/5/19 17:26
 */
@Component
public class SpringBeanFactory implements ApplicationContextAware {

    private static ApplicationContext context;

    public static <T> T getBean(Class<T> c) {
        return context.getBean(c);
    }

    public static <T> T getBean(Class<T> c, String name) {
        return context.getBean(c, name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
