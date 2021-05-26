package org.margomalanuha.spring.labs.controllers;

import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@NoArgsConstructor
@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public List<Catalog> getAllCatalogs() {
        return catalogService.getAllCatalogs();
    }
    public List<Catalog> getSubdirectoriesById(int id) { return catalogService.getSubdirectoriesById(id); }
    public void createCatalog(String title, int upperCatalogId) {
        catalogService.createCatalog(title, upperCatalogId);
    }
    public void updateCatalog(Catalog catalog) {
        catalogService.updateCatalog(catalog);
    }
    public void deleteCatalog(int id) {
        catalogService.deleteCatalog(id);
    }
    public void createCatalogInCatalog(String title, Catalog catalog) { catalogService.createCatalogInCatalog(title, catalog);}

}
