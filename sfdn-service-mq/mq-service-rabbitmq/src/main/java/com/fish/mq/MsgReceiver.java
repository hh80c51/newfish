package com.fish.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author hh
 * @description
 * @date 2020/11/7  0:59
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MsgReceiver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String content) {
        logger.info("接收处理队列A当中的消息： " + content);
    }

}
