package com.example.alarmandlogin.service.OAuth2;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Base64;

@Service
public class TokenService {

    private String secretKey = "token-secret-key";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
}
