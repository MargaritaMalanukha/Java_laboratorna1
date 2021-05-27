package org.margomalanuha.spring.labs.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"org.margomalanuha.spring.labs.repository",
        "org.margomalanuha.spring.labs.service", "org.margomalanuha.spring.labs.controllers",
        "org.margomalanuha.spring.labs.ui", "org.margomalanuha.spring.labs.models.pojo"})
@EnableAutoConfiguration
public class Config {

    @Bean
    public WebApplicationContext webApplicationContext() {
        return new ServletWebServerApplicationContext();
    }

}
