package org.margomalanuha.spring.labs.controllers;

import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

    public List<Catalog> getSubdirectoriesById(Integer id) {
        return catalogService.getSubdirectoriesById(id);
    }

    public void createCatalog(String title, Integer catalogId) {
        catalogService.createCatalog(title, catalogId);
    }

    public void updateCatalog(Integer catalogId) {
        catalogService.updateCatalog(catalogId);
    }

    public void deleteCatalog(Integer catalogId) {
        catalogService.deleteCatalog(catalogId);
    }

    public void createCatalogInCatalog(String title, Integer catalogId) {
        catalogService.createCatalogInCatalog(title, catalogId);
    }

    public Catalog getCatalogById(Integer catalogId) {
        return catalogService.getCatalogById(catalogId);
    }

    public Catalog findCatalogByTitle(String title) {
        return catalogService.findCatalogByTitle(title);
    }

}
