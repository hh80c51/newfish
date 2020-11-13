package com.fish.design;

import com.fish.design.proxy.JDKProxy;
import com.fish.design.adapter.impl.EGMCacheAdapter;
import com.fish.design.adapter.impl.IIRCacheAdapter;
import com.fish.design.service.impl.CacheServiceImpl;
import com.fish.design.service.CacheService;
import org.junit.Test;

public class ApiTest {

    /**
     * @description 抽象工厂模式重构代码
     * 场景:多套Redis缓存集群升级
     * EGM和IIR是两套redis集群，这两套集群缓存的存取方法名不同，现在需要兼容使用
     * 解决方式：写一个适配接口，由EGM和IIR实现适配接口，里面对应各自不同的实现，这一步是为了统一方法名称
     * 写一个代理方法，传入对外服务实现类，和适配接口实现类（要代理的真实对象），生成对外服务
     * 由对外服务统一进行存取方法的操作
     * @param
     * @return void
     * @date 2020/10/20 10:05
     * @author hehang
     */
    @Test
    public void test_CacheService() throws Exception {

        CacheService proxy_EGM = JDKProxy.getProxy(CacheServiceImpl.class, new EGMCacheAdapter());
        proxy_EGM.set("user_name_01", "小傅哥");
        String val01 = proxy_EGM.get("user_name_01");
        System.out.println("测试结果：" + val01);

        CacheService proxy_IIR = JDKProxy.getProxy(CacheServiceImpl.class, new IIRCacheAdapter());
        proxy_IIR.set("user_name_01", "小傅哥");
        String val02 = proxy_IIR.get("user_name_01");
        System.out.println("测试结果：" + val02);

    }

}
