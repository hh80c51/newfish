package com.fish.design;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @description MQ消息体适配类
 *
 * @date 2020/10/20 12:18
 * @author hehang
 */
public class MQAdapter {

    /**
     * @description 主要用于把不同类型MQ种的各种属性，映射成我们需要的属性并返回
     * @param strJson
     * @param link 我们需要的属性
     * @return org.itstack.demo.design.RebateInfo
     * @date 2020/10/20 12:23
     * @author hehang
     */
    public static RebateInfo filter(String strJson, Map<String, String> link) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return filter(JSON.parseObject(strJson, Map.class), link);
    }

    public static RebateInfo filter(Map obj, Map<String, String> link) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RebateInfo rebateInfo = new RebateInfo();
        for (String key : link.keySet()) {
            Object val = obj.get(link.get(key));
            RebateInfo.class.getMethod("set" + key.substring(0, 1).toUpperCase() + key.substring(1), String.class).invoke(rebateInfo, val.toString());
        }
        return rebateInfo;
    }

}
