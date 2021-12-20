package com.example.websockectandconcurrentlogin.controller;

import com.example.websockectandconcurrentlogin.domain.AlarmRequest;
import com.example.websockectandconcurrentlogin.service.jms.Sender;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jms")
public class JmsController implements ApplicationRunner {

    @Resource
    private Sender sender;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        sender.send("Hello Spring JMS ActiveMQ!");
    }

    @GetMapping
    public void testGetSend() {
        sender.send("Hello Spring JMS ActiveMQ!");
    }

    @PostMapping
    public void testPostSend(AlarmRequest alarmRequest) {
        sender.send("\n[포들리 예약취소]\n" + alarmRequest.getContent());
    }

//    @Scheduled(fixedRate = 2000) //2sec
//    public void sendTime() {
//        sender.timeSend();
//    }
}
