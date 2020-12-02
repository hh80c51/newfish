package com.fish.demo.json2bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName Transfer
 * @Description TODO
 * @Author 86131
 * @Date 2020/12/2 18:31
 * @Version 1.0
 **/
@Slf4j
public class Transfer {

    /**
     * @description 实体类，修改命名格式，转json
     * @param
     * @return java.lang.String
     * @date 2020/12/2 18:36
     * @author hehang
     */
    public String bean2JsonHandle(Object object){
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        String json = JSON.toJSONString(object, config);
        log.info("转换的结果：{}",json);
        return json;
    }

    /**
     * @description 两个类名称格式不同，属性赋值
     * @param sourceObj
     * @param targetClass
     * @return T
     * @date 2020/12/2 18:39
     * @author hehang
     */
    public static <T, H> T copyProperty(H sourceObj, Class targetClass) {
        try {
            T targetObj = (T) targetClass.newInstance();
            Field[] targetFields = targetClass.getDeclaredFields();
            Field[] sourceFields = sourceObj.getClass().getDeclaredFields();
            PropertyDescriptor sfpd = null;
            Method readMehtod = null;
            PropertyDescriptor tfpd = null;
            Method setMethod = null;
            for (Field targetField : targetFields) {
                for (Field sourceField : sourceFields) {
                    //这里写名称映射的条件
                    if (targetField.getName().substring(1).equals(sourceField.getName().substring(3))) {
                        sfpd = new PropertyDescriptor(sourceField.getName(), sourceObj.getClass());
                        readMehtod = sfpd.getReadMethod();
                        tfpd = new PropertyDescriptor(targetField.getName(), targetClass);
                        setMethod = tfpd.getWriteMethod();
                        // 这里只是粗略地做了一下判断...
                        if (readMehtod.getReturnType().equals(setMethod.getParameterTypes()[0])) {
                            setMethod.invoke(targetObj, readMehtod.invoke(sourceObj));
                        }
                    }
                }
            }
            return targetObj;
        } catch (Exception e) {
            return null;
        }
    }
}
