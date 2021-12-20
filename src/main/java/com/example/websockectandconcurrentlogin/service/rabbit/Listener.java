package com.example.websockectandconcurrentlogin.service.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    @RabbitListener(queues = "sample.queue")
    public void receiveMessage(Message message) {
        logger.info("received message : {}", message);
    }
}
