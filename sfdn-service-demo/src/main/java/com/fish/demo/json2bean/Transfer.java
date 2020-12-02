package com.fish.demo.json2bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import lombok.extern.slf4j.Slf4j;

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
}
