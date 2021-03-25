package org.margomalanuha.spring.labs.service;

import lombok.AllArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.repository.CatalogRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    public CatalogServiceImpl() { this.catalogRepository = new CatalogRepository(); }

    @Override
    public List<Catalog> getAllCatalogs() {
        return catalogRepository.getAll();
    }

    @Override
    public List<Catalog> getSubdirectoriesById(int id) {
        return catalogRepository.getAll()
                .stream()
                .filter(e -> e.getUpperCatalogId() == id)
                .collect(Collectors.toList());
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
