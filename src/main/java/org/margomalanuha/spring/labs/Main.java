package org.margomalanuha.spring.labs;

import org.margomalanuha.spring.labs.config.Config;
import org.margomalanuha.spring.labs.controllers.AdminController;
import org.margomalanuha.spring.labs.service.ProductsService;
import org.margomalanuha.spring.labs.service.ProductsServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        AdminController adminController = context.getBean("adminController", AdminController.class);
        ProductsServiceImpl productsService = context.getBean("productsServiceImpl", ProductsServiceImpl.class);
        System.out.println("here");

        context.close();
    }

}
