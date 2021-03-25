package org.margomalanuha.spring.labs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.margomalanuha.spring.labs.repository",
        "org.margomalanuha.spring.labs.service", "org.margomalanuha.spring.labs.controllers"})
public class Config {



}
