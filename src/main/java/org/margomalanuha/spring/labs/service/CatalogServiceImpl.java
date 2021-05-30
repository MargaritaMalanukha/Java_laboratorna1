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
import java.util.NoSuchElementException;
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
    public List<Catalog> getSubdirectoriesById(Integer id) {
        if (id == null) id = 1;
        Integer finalId = id;
        List<Catalog> allCatalogs = catalogRepository.findAll();
        allCatalogs.remove(0); //вызовет NPE
        return allCatalogs
                .stream()
                .filter(e -> e.getCatalog().getId() == finalId)
                .collect(Collectors.toList());
    }

    @Override
    public void createCatalog(String title, Integer catalogId) {
        Catalog catalog = new Catalog();
        catalog.setId(catalogId);
        catalogRepository.save(new Catalog(title, catalog));
    }

    @Override
    public void updateCatalog(Integer catalogId) {
        Catalog catalog = new Catalog();
        catalog.setId(catalogId);
        catalogRepository.save(catalog);
    }

    @Override
    public void deleteCatalog(Integer catalogId) {
        Catalog catalog = new Catalog();
        catalog.setId(catalogId);
        catalogRepository.delete(catalog);
    }

    @Override
    public void createCatalogInCatalog(String title, Integer catalogId) {
        Catalog catalog = new Catalog();
        catalog.setId(catalogId);
        catalogRepository.save(new Catalog(title, catalog));
    }

    @Override
    public Catalog getCatalogById(Integer id) {
        return catalogRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Catalog findCatalogByTitle(String title) {
        return catalogRepository.findAll().stream()
                .filter(e -> e.getTitle().equalsIgnoreCase(title))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }


}
