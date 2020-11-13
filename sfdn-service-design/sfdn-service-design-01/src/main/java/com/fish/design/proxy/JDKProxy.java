package com.fish.design.proxy;

import com.fish.design.adapter.ICacheAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @description 代理类的定义和实现，这部分也就是抽象工厂的另外一种实现方式
 * 这里主要的作用就是完成代理类，同时对于使用哪个集群由外部通过入参进行传递。
 * @date 2020/10/20 10:07
 * @author hehang
 */
public class JDKProxy {

    /**
     * @description 方法描述
     * @param interfaceClass 
     * @param cacheAdapter：要代理的真实对象
     * @return T
     * @date 2020/10/20 10:21
     * @author hehang
     */
    public static <T> T getProxy(Class<T> interfaceClass, ICacheAdapter cacheAdapter) throws Exception {
        //代理对象的调用处理程序，我们将要代理的真实对象传入代理对象的调用处理的构造函数中，最终代理对象的调用处理程序会调用真实对象的方法
        InvocationHandler handler = new JDKInvocationHandler(cacheAdapter);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        ClassLoader classLoader = handler.getClass().getClassLoader();
        /**
         * 通过Proxy类的newProxyInstance方法创建代理对象，我们来看下方法中的参数
         * 第一个参数：people.getClass().getClassLoader()，使用handler对象的classloader对象来加载我们的代理对象
         * 第二个参数：people.getClass().getInterfaces()，这里为代理类提供的接口是真实对象实现的接口，这样代理对象就能像真实对象一样调用接口中的所有方法
         * 第三个参数：handler，我们将代理对象关联到上面的InvocationHandler对象上
         */
        Class<?>[] classes = interfaceClass.getInterfaces();
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{classes[0]}, handler);
    }

}
