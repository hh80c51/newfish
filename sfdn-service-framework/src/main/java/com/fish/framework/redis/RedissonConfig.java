package com.fish.framework.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * redisson bean管理
 */
@Configuration
public class RedissonConfig {

    @Autowired
    private Environment env;

    /**
     * Redisson客户端注册
     * 单机模式
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient createRedissonClient() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://" + env.getProperty("spring.redis.host") + ":" + env.getProperty("spring.redis.port"));
        singleServerConfig.setTimeout(Integer.valueOf(env.getProperty("redisson.timeout")));
        return Redisson.create(config);
    }

    /**
     * 分布式锁实例化并交给工具类
     * @param redissonClient
     */
    @Bean
    public RedissonDistributeLocker redissonLocker(RedissonClient redissonClient) {
        RedissonDistributeLocker locker = new RedissonDistributeLocker(redissonClient);
        RedissonLockUtils.setLocker(locker);
        return locker;
    }

}