package com.example.websockectandconcurrentlogin.service.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import java.time.LocalDateTime;

public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String message) {
        LOGGER.info("sending message='{}'", message);
        jmsTemplate.convertAndSend("hello-world-queue", message);
    }

//    public void sendObject(Alarm alarm) {
//        LOGGER.info("sending message='{}'", alarm.toString());
//        jmsTemplate.convertAndSend("hello-world-queue", alarm, this::addMessageSource);
//    }

//    private Message addMessageSource(Message message) throws JMSException {
//        message.setStringProperty("X_ALARM_SOURCE", "WEB");
//        return message;
//    }

    public void timeSend() {
        LOGGER.info("sending message='{}'", "Current Date & Time is : " + LocalDateTime.now());
        jmsTemplate.convertAndSend("time-queue", "Current Date & Time is : " + LocalDateTime.now());
    }
}
