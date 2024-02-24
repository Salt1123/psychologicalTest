package com.boot.springboot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors() // CORS를 활성화합니다.
                .and()
                .csrf().disable() // CSRF 보호를 비활성화합니다.
                .authorizeRequests()
                .antMatchers("/boot/login").permitAll() // /boot/login에 대한 요청은 모든 사용자에게 허용합니다.
                .anyRequest().authenticated(); // 그 외의 모든 요청은 인증된 사용자만 허용합니다.
    }
}


    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .cors().and()
                .authorizeRequests()
                .antMatchers("/boot/login").permitAll() // /boot/login 엔드포인트에 대한 접근 권한을 모든 사용자에게 허용
                .anyRequest().authenticated() // 다른 모든 요청은 인증이 필요함
                .and()
                .csrf().disable(); // CSRF 보호 비활성화
    }
}*/