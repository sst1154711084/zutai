package com.sst.projectService.config;

import com.sst.handler.CheckParamsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    CheckParamsInterceptor checkParamsInterceptor = new CheckParamsInterceptor();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkParamsInterceptor);
    }
}
