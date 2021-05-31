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
@RequestMapping("/api/catalogs")
public class CatalogController {

    private CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public List<Catalog> getAllCatalogs() {
        return catalogService.getAllCatalogs();
    }

    @GetMapping("/{id}")
    public List<Catalog> getSubdirectoriesById(@PathVariable Integer id) {
        return catalogService.getSubdirectoriesById(id);
    }

    @PostMapping
    public void createCatalog(String title, Integer catalogId) {
        catalogService.createCatalog(title, catalogId);
    }

    @PutMapping
    public void updateCatalog(Integer catalogId) {
        catalogService.updateCatalog(catalogId);
    }

    @DeleteMapping("/{catalogId}")
    public void deleteCatalog(@PathVariable Integer catalogId) {
        catalogService.deleteCatalog(catalogId);
    }

    @PostMapping("/catalog")
    public void createCatalogInCatalog(@RequestParam String title, @RequestParam Integer catalogId) {
        catalogService.createCatalogInCatalog(title, catalogId);
    }

    @GetMapping("/catalog/{catalogId}")
    public Catalog getCatalogById(@PathVariable Integer catalogId) {
        return catalogService.getCatalogById(catalogId);
    }

    @GetMapping("/catalog/title/{title}")
    public Catalog findCatalogByTitle(@PathVariable String title) {
        return catalogService.findCatalogByTitle(title);
    }

}
