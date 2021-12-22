package com.example.alarmandlogin.service.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

import java.util.concurrent.CountDownLatch;

public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @JmsListener(destination = "hello-world-queue")
    public void receive(String message) {
        logger.info("received message='{}'", message);
    }

    @JmsListener(destination = "time-queue")
    public void timeReceive(String message) {
        logger.info("received message='{}'", message);
    }
}
