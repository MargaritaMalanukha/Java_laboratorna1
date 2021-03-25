package org.margomalanuha.spring.labs.controllers;

import lombok.AllArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.service.CatalogService;
import org.margomalanuha.spring.labs.service.CatalogServiceImpl;

import java.util.List;

@AllArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController() {
        this.catalogService = new CatalogServiceImpl();
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
