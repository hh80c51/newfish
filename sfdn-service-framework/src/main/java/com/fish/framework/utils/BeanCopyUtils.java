package com.fish.framework.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName BeanCopyUtils
 * @Description 基于BeanCopier的属性拷贝
 * @Author 86131
 * @Date 2020/11/14 11:39
 * @Version 1.0
 * 凡是和反射相关的操作，基本都是低性能的。凡是和字节码相关的操作，基本都是高性能的。
 **/

@Slf4j
public class BeanCopyUtils {

    /**
     * 创建过的BeanCopier实例放到缓存中，下次可以直接获取，提升性能
     */
    private static final Map<String, BeanCopier> BEAN_COPIERS = new ConcurrentHashMap<>();

    /**
     * 该方法没有自定义Converter,简单进行常规属性拷贝
     *
     * @param srcObj  源对象
     * @param destObj 目标对象
     */
    public static void copy(Object srcObj, Object destObj) {
        String key = genKey(srcObj.getClass(), destObj.getClass());
        BeanCopier copier;
        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), false);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        copier.copy(srcObj, destObj, null);
    }

    private static String genKey(Class<?> srcClazz, Class<?> destClazz) {
        return srcClazz.getName() + destClazz.getName();
    }
}
