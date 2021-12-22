package com.example.websockectandconcurrentlogin.controller;

import com.example.websockectandconcurrentlogin.service.firebase.FirebaseService;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/firebase")
public class FirebaseController {

    private final FirebaseService firebaseService;

    @GetMapping
    public String initialize() {
        firebaseService.initialize();
        return "firebase-initialize";
    }

    @GetMapping("/send-message")
    public String sendMessage(String token) throws IOException {
        firebaseService.sendMessage(buildNotificationMessage(token));
        return "firebase-send-message";
    }

    private JsonObject buildNotificationMessage(String token) {
        JsonObject jNotification = new JsonObject();
        //jNotification.addProperty("title", message.getTitle());
        jNotification.addProperty("body", "안녕하세요");

        JsonObject jMessage = new JsonObject();

        jMessage.add("notification", jNotification);
        jMessage.addProperty("token", token);
        //jMessage.addProperty("topic", "news");

        JsonObject jFcm = new JsonObject();
        jFcm.add("message", jMessage);

        return jFcm;
    }
}
