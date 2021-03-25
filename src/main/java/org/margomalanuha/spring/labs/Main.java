package org.margomalanuha.spring.labs;

import org.margomalanuha.spring.labs.controllers.CatalogController;
import org.margomalanuha.spring.labs.models.pojo.Catalog;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        CatalogController catalogController = new CatalogController();
        List<Catalog> catalogs = catalogController.getSubdirectoriesById(0);
        catalogs.forEach(System.out::println);
    }

}
