package org.margomalanuha.spring.labs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.vaadin.artur.helpers.LaunchUtil;

@SpringBootApplication(scanBasePackages = {"org.margomalanuha.spring.labs.controllers", "org.margomalanuha.spring.labs.ui",
        "org.margomalanuha.spring.labs.service", "org.margomalanuha.spring.labs.repository" })
@EntityScan(basePackages = {"org.margomalanuha.spring.labs.models.pojo"} )
@EnableJpaRepositories(basePackages = {"org.margomalanuha.spring.labs.repository"})
public class SpringBootRunner extends SpringBootServletInitializer {

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(SpringBootRunner.class, args));
    }


}
