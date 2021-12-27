package com.example.alarmandlogin.controller;

import com.example.alarmandlogin.service.OAuth2.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/kakao")
public class AuthController {

    private final AuthService authService;

//    @PostMapping("/logout")
//    public String logout(HttpSession httpSession) throws IOException {
//        String accessToken = (String) httpSession.getAttribute("access_token");
//        if (StringUtils.hasText(accessToken)) {
//            httpSession.removeAttribute("access_token");
//            httpSession.removeAttribute("member");
//            return "index";
//        }
//        return null;
//    }

    @GetMapping("/login")
    public String login() {
        return "kakao";
    }

    @GetMapping("/login/oauth2/code/kakao")
    public String loginSuccess(@RequestParam("code") String code) {
        System.out.println("code = " + code);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpSession httpSession) {
        authService.logout((String) httpSession.getAttribute("access_token"));
        httpSession.invalidate();
        return "redirect:/";
    }
}
