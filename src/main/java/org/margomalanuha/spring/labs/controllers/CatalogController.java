package org.margomalanuha.spring.labs.controllers;

import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CatalogController {

    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public List<Catalog> getAllCatalogs() {
        return catalogService.getAllCatalogs();
    }
    public List<Catalog> getSubdirectoriesById(int id) { return catalogService.getSubdirectoriesById(id); }
    public void createCatalog(String title) {
        catalogService.createCatalog(title);
    }
    public void updateCatalog(Catalog catalog) {
        catalogService.updateCatalog(catalog);
    }
    public void deleteCatalog(int id) {
        catalogService.deleteCatalog(id);
    }

}
