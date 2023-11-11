package com.toy.codingtest.components.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 테스트용으로 모든 Origin으로부터의 CORS를 허용시키기 위해서
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry
        .addMapping("/**")
        .allowedOrigins("*")
        .exposedHeaders("Authorization");
    }

}