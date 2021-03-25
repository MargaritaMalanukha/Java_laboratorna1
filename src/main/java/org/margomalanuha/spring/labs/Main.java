package org.margomalanuha.spring.labs;

import org.margomalanuha.spring.labs.controllers.AdminController;
import org.margomalanuha.spring.labs.models.pojo.Catalog;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        AdminController adminController = new AdminController();
        List<Catalog> catalogs = adminController.getAllCatalogs();
        catalogs.forEach(System.out::println);
    }

}
