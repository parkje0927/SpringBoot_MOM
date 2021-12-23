package com.example.alarmandlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/api/kakao")
public class AuthController {

    @PostMapping("/logout")
    public String logout(HttpSession httpSession) throws IOException {
        String accessToken = (String) httpSession.getAttribute("access_token");
        if (StringUtils.hasText(accessToken)) {
            httpSession.removeAttribute("access_token");
            httpSession.removeAttribute("member");
            return "index";
        }
        return null;
    }
}
