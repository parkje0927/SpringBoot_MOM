package com.example.websockectandconcurrentlogin.service.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @JmsListener(destination = "hello-world-queue")
    public void receive(String message) {
        logger.info("received message='{}'", message);
    }

    @JmsListener(destination = "time-queue")
    public void timeReceive(String message) {
        logger.info("received message='{}'", message);
    }
}
