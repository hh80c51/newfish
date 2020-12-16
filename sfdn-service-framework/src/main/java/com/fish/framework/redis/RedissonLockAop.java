package com.fish.framework.redis;

import com.alibaba.fastjson.JSONObject;
import com.fish.framework.object.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁/防重复提交 aop
 */
@Aspect
@Component
@Slf4j
public class RedissonLockAop {

    /**
     * 切点，拦截被 @RedissonLockAnnotation 修饰的方法
     */
    @Pointcut("@annotation(RedissonLockAnnotation)")
    public void redissonLockPoint() {
    }

    @Around("redissonLockPoint()")
    @ResponseBody
    public ResponseVO checkLock(ProceedingJoinPoint pjp) throws Throwable {
        //当前线程名
        String threadName = Thread.currentThread().getName();
        log.info("线程{}------进入分布式锁aop------", threadName);
        //获取参数列表
        Object[] objs = pjp.getArgs();
        //因为只有一个JSON参数，直接取第一个
        JSONObject param = (JSONObject) objs[0];
        //获取该注解的实例对象
        RedissonLockAnnotation annotation = ((MethodSignature) pjp.getSignature()).
                getMethod().getAnnotation(RedissonLockAnnotation.class);
        //生成分布式锁key的键名，以逗号分隔
        String keyParts = annotation.keyParts();
        StringBuffer keyBuffer = new StringBuffer();
        if (StringUtils.isEmpty(keyParts)) {
            log.info("线程{} keyParts设置为空，不加锁", threadName);
            return (ResponseVO) pjp.proceed();
        } else {
            //生成分布式锁key
            String[] keyPartArray = keyParts.split(",");
            for (String keyPart : keyPartArray) {
                keyBuffer.append(param.getString(keyPart));
            }
            String key = keyBuffer.toString();
            log.info("线程{} 要加锁的key={}", threadName, key);
            //获取锁
            if (RedissonLockUtils.tryLock(key, 3000, 5000, TimeUnit.MILLISECONDS)) {
                try {
                    log.info("线程{} 获取锁成功", threadName);
                    return (ResponseVO) pjp.proceed();
                } finally {
                    RedissonLockUtils.unlock(key);
                    log.info("线程{} 释放锁", threadName);
                }
            } else {
                log.info("线程{} 获取锁失败", threadName);
                return new ResponseVO(-9, "请求超时");
            }
        }

    }
}