package com.example.alarmandlogin.config;

import com.example.alarmandlogin.security.CustomConcurrentSessionStrategy;
import com.example.alarmandlogin.service.OAuth2.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll()

                .and()
                .httpBasic().disable()
                .csrf().disable()
                .cors().disable()
                .headers().frameOptions().disable()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .oauth2Login()
                .userInfoEndpoint() //oauth2 로그인 성공 후 가져올 때의 설정들
                .userService(customOAuth2UserService) //

//                .sessionManagement()
//                .maximumSessions(4)
//                .maxSessionsPreventsLogin(true) //true : 나중에 접속한 사용자 로그인 방지 / false : 먼저 접속한 사용자 logout 처리
//                .expiredUrl("/duplicated-login")
//                .sessionRegistry(sessionRegistry()) //-> 사용자가 logout 후 다시 login 할 때, "Maximum sessions of 1 for this principal exceeded" 에러를 방지
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CustomConcurrentSessionStrategy sessionControlStrategy() {
//        return new CustomConcurrentSessionStrategy(sessionRegistry());
//    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
