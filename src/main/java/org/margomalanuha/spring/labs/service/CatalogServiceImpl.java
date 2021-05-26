package org.margomalanuha.spring.labs.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Getter
public class CatalogServiceImpl implements CatalogService {

    private CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

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
    public void createCatalog(String title, int upperCatalogId) {
        catalogRepository.create(new Catalog(title, upperCatalogId));
    }

    @Override
    public void updateCatalog(Catalog catalog) {
        catalogRepository.update(catalog);
    }

    @Override
    public void deleteCatalog(int id) {
        catalogRepository.delete(id);
    }

    @Override
    public void createCatalogInCatalog(String title, Catalog catalog) {
        catalogRepository.create(new Catalog(title, catalog.getId()));
    }
}
