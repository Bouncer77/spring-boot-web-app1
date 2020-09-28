package com.bouncer77.springbootapp1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 03.09.2020
 * */

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /*public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }*/

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/img/**")
        .addResourceLocations("file://" + uploadPath + "/"); // искать в файловой системе;


        /*registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/"); // искать в дереве проекта*/

        registry.addResourceHandler("/upload/**")
                .addResourceLocations("classpath:/upload/"); // искать в дереве проекта
    }
}
