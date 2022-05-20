package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    //设置CORS，允许跨域
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 放行全部请求类型
                .allowedMethods("*")
                //暴露哪些头部信息
                .exposedHeaders("*")
                // 是否发送Cookie
                .allowCredentials(true);
    }
}
