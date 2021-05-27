package org.margomalanuha.spring.labs.service;

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
        return catalogRepository.findAll();
    }

    @Override
    public List<Catalog> getSubdirectoriesById(int id) {
        return catalogRepository.findAll()
                .stream()
                .filter(e -> e.getCatalog().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public void createCatalog(String title, Catalog catalog) {
        catalogRepository.save(new Catalog(title, catalog));
    }

    @Override
    public void updateCatalog(Catalog catalog) {
        catalogRepository.save(catalog);
    }

    @Override
    public void deleteCatalog(Catalog catalog) {
        catalogRepository.delete(catalog);
    }

    @Override
    public void createCatalogInCatalog(String title, Catalog catalog) {
        catalogRepository.save(new Catalog(title, catalog));
    }
}
