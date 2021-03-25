package org.margomalanuha.spring.labs;

import org.margomalanuha.spring.labs.config.Config;
import org.margomalanuha.spring.labs.controllers.AdminController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        AdminController adminController = context.getBean("adminController", AdminController.class);
        AdminController adminController1 = context.getBean("adminController", AdminController.class);
        System.out.println(adminController == adminController1);

        context.close();
    }

}
