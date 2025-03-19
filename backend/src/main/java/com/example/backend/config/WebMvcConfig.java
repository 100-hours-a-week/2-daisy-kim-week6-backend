package com.example.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /uploads/** 경로 요청을 실제 저장된 파일 경로로 매핑
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/Users/kdh/Desktop/hw_image/");
    }
}
