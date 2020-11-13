package com.fish.design.proxy;

import com.fish.design.adapter.ICacheAdapter;
import com.fish.design.util.ClassLoaderUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description 每一个动态代理类的调用处理程序都必须实现InvocationHandler接口
 * @date 2020/10/20 10:17
 * @author hehang
 */
public class JDKInvocationHandler implements InvocationHandler {

    private ICacheAdapter cacheAdapter;

    public JDKInvocationHandler(ICacheAdapter cacheAdapter) {
        this.cacheAdapter = cacheAdapter;
    }

    /**
     * @description 通过使用获取方法名称反射方式，调用对应的方法功能，也就简化了整体的使用。
     * @param proxy:代理类代理的真实代理对象com.sun.proxy.$Proxy0
     * @param method:我们所要调用某个对象真实的方法的Method对象
     * @param args:指代理对象方法传递的参数
     * @return java.lang.Object
     * @date 2020/10/20 10:12
     * @author hehang
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return ICacheAdapter.class.getMethod(method.getName(), ClassLoaderUtils.getClazzByArgs(args)).invoke(cacheAdapter, args);
    }

}
