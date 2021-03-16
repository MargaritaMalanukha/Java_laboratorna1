package org.margomalanuha.spring.labs.service;

import lombok.AllArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.repository.CatalogRepository;

import java.util.List;

@AllArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    public CatalogServiceImpl() { this.catalogRepository = new CatalogRepository(); }

    @Override
    public List<Catalog> getAllCatalogs() {
        return catalogRepository.getAll();
    }

    @Override
    public void createCatalog(String title) {
        catalogRepository.create(new Catalog(title));
    }

    @Override
    public void updateCatalog(Catalog catalog) {
        catalogRepository.update(catalog);
    }

    @Override
    public void deleteCatalog(int id) {
        catalogRepository.delete(id);
    }
}
