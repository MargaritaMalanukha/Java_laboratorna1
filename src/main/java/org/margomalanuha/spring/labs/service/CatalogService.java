package org.margomalanuha.spring.labs.service;

import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CatalogService {

    List<Catalog> getAllCatalogs();
    List<Catalog> getSubdirectoriesById(Integer id);
    void createCatalog(String title, Integer catalogId);
    void updateCatalog(Integer catalogId);
    void deleteCatalog(Integer catalogId);
    void createCatalogInCatalog(String title, Integer catalogId);
    Catalog getCatalogById(Integer id);
    Catalog findCatalogByTitle(String title);

}
