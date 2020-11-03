package com.fish.framework.holder;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 这个类可以直接获取spring 配置文件中 所有引用（注入）到的bean对象
 **/
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContextHope = null;

    /***
     * 根据name获取bean
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return applicationContextHope.getBean(name);
    }

    /***
     * 根据class获取bean
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> tClass) {
        return applicationContextHope.getBean(tClass);
    }

    /***
     * 根据name，指定class返回Bean
     * @param name
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> tClass) {
        return applicationContextHope.getBean(name, tClass);
    }

    /***
     * 重写
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContextHope == null) {
            applicationContextHope = applicationContext;
        }
    }
}