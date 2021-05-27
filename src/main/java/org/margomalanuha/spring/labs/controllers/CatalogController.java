package org.margomalanuha.spring.labs.controllers;

import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@NoArgsConstructor
@RestController
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
    public void createCatalog(String title, Catalog catalog) {
        catalogService.createCatalog(title, catalog);
    }
    public void updateCatalog(Catalog catalog) {
        catalogService.updateCatalog(catalog);
    }
    public void deleteCatalog(Catalog catalog) {
        catalogService.deleteCatalog(catalog);
    }
    public void createCatalogInCatalog(String title, Catalog catalog) { catalogService.createCatalogInCatalog(title, catalog);}

}
