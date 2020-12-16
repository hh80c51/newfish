package com.fish.framework.redis;

import com.alibaba.fastjson.JSONObject;
import com.fish.framework.object.ResponseVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author hh
 * @description
 * @date 2020/12/16  23:40
 */
public class TestLock {
    @PostMapping(value = "testLock", consumes = "application/json")
    @RedissonLockAnnotation(keyParts = "name,age")
    public ResponseVO testLock(@RequestBody JSONObject params) {
        /**
         * 分布式锁key=params.getString("name")+params.getString("age");
         * 此时name和age均相同的请求不会出现并发问题
         */
        //TODO 业务处理
        return new ResponseVO(0, "SUCCESS");
    }


    public static void main(String[] args) {

    }
}
