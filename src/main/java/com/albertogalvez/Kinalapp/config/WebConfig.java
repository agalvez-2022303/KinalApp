package com.albertogalvez.Kinalapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Spring Security reemplaza al LoginInterceptor manual
@Configuration
public class WebConfig implements WebMvcConfigurer {
}