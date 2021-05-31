package org.margomalanuha.spring.labs.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.repository.CatalogRepository;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CatalogServiceImplTest {

    private CatalogRepository repository;
    private CatalogServiceImpl catalogService;

    @Before
    public void mock() {
        repository = Mockito.mock(CatalogRepository.class);
        catalogService = new CatalogServiceImpl(repository);
    }

    @Test
    public void getAllCatalogs_whenCatalogsAreInDatabase_returnListOfAllCatalogs() {
        //GIVEN
        List<Catalog> catalogs = new LinkedList<>();
        Catalog milkProducts = new Catalog(2, "milk products", new Catalog(1, "Primary", new Catalog()));
        catalogs.add(milkProducts);
        catalogs.add(new Catalog(3, "sweet products", new Catalog(1, "Primary", new Catalog())));
        catalogs.add(new Catalog(4, "cheeses", milkProducts));
        Mockito.doReturn(catalogs).when(repository).findAll();

        //WHEN
        List<Catalog> actual = catalogService.getAllCatalogs();

        //THEN
        Mockito.verify(repository, Mockito.times(1)).findAll();
        Assertions.assertEquals(catalogs, actual);
    }

    @Test
    public void getSubdirectoriesById_whenCatalogsAreInDatabase_returnListOfSubdirectoriesOfCatalog() {
        //GIVEN
        Catalog catalog = new Catalog(1, "milk products", new Catalog());
        List<Catalog> catalogs = new LinkedList<>();
        catalogs.add(catalog);
        catalogs.add(new Catalog(2, "cheeses", catalog));
        catalogs.add(new Catalog(3, "butters", catalog));
        catalogs.add(new Catalog(4, "fishes", new Catalog()));
        Mockito.doReturn(catalogs).when(repository).findAll();


        //WHEN
        List<Catalog> actual = catalogService.getSubdirectoriesById(1);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).findAll();
        catalogs.remove(2);
        Assertions.assertEquals(catalogs, actual);
    }

    @Test
    public void createCatalog_whenDataIsCorrect_createNewCatalog() {
        //GIVEN
        String title = "milk products";
        int upperCatalogId = 12;
        Catalog catalog = new Catalog();
        catalog.setId(upperCatalogId);

        //WHEN
        catalogService.createCatalog(title, upperCatalogId);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).save(new Catalog(title, catalog));
    }

    @Test
    public void updateCatalog_whenDataIsCorrect_updateCatalog() {
        //GIVEN
        int id = 10;
        Catalog catalog = new Catalog();
        catalog.setId(id);
        Mockito.doReturn(catalog).when(repository).save(catalog);

        //WHEN
        catalogService.updateCatalog(id);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).save(catalog);
    }

    @Test
    public void deleteCatalog_whenCatalogExists_deleteCatalog() {
        //GIVEN
        Catalog catalog = new Catalog();
        catalog.setId(1);

        //WHEN
        catalogService.deleteCatalog(catalog.getId());

        //THEN
        Mockito.verify(repository, Mockito.times(1)).delete(catalog);
    }

    @Test
    public void createCatalogInCatalog_whenCatalogExists_createNewSubCatalog() {
        //GIVEN
        String title = "cheeses";
        Catalog catalog = new Catalog();
        catalog.setId(1);

        //WHEN
        catalogService.createCatalog(title, catalog.getId());

        //THEN
        Mockito.verify(repository, Mockito.times(1)).save(new Catalog(title, catalog));
    }

    @Test
    public void getCatalogById_whenCatalogExists_returnCatalog() {
        //GIVEN
        int catalogId = 12;
        Catalog catalog = new Catalog("milk", new Catalog());
        catalog.setId(catalogId);
        Mockito.doReturn(Optional.of(catalog)).when(repository).findById(catalogId);

        //WHEN
        Catalog actual = catalogService.getCatalogById(catalogId);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).findById(catalogId);
        Assertions.assertEquals(catalog, actual);
    }

    @Test
    public void findCatalogByTitle_whenCatalogExistsAndTitleIsCorrect_returnCatalog() {
        //GIVEN
        String title = "milk product";
        List<Catalog> catalogs = new LinkedList<>();
        catalogs.add(new Catalog("milk", new Catalog()));
        catalogs.add(new Catalog(title, new Catalog()));
        catalogs.add(new Catalog(title, new Catalog("milk", new Catalog())));
        Mockito.doReturn(catalogs).when(repository).findAll();

        //WHEN
        Catalog catalog = catalogService.findCatalogByTitle(title);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).findAll();
        Assertions.assertEquals(catalogs.get(1), catalog);
    }


}
