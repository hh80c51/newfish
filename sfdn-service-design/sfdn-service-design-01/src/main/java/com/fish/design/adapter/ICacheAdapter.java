package com.fish.design.adapter;

import java.util.concurrent.TimeUnit;

/**
 * @description 定义了适配接口，分别包装两个集群中差异化的接口名称
 * @date 2020/10/20 10:06
 * @author hehang
 */
public interface ICacheAdapter {

    String get(String key);

    void set(String key, String value);

    void set(String key, String value, long timeout, TimeUnit timeUnit);

    void del(String key);

}
