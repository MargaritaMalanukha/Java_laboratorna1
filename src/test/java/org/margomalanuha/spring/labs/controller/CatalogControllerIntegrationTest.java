package org.margomalanuha.spring.labs.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.margomalanuha.spring.labs.config.Config;
import org.margomalanuha.spring.labs.controllers.CatalogController;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.repository.CatalogRepository;
import org.margomalanuha.spring.labs.service.CatalogService;
import org.margomalanuha.spring.labs.service.CatalogServiceImpl;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SpringBootTest(classes = {Config.class})
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class CatalogControllerIntegrationTest {

    private CatalogController catalogController;

    @Before
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        CatalogRepository catalogRepository = context.getBean(CatalogRepository.class);
        CatalogService catalogService = new CatalogServiceImpl(catalogRepository);
        catalogController = new CatalogController(catalogService);
    }

    @Test
    public void getSubdirectoriesById_whenSubdirectoriesExist_return() {
        //GIVEN
        int catalogId = 1;

        //WHEN
        List<Catalog> actual = catalogController.getSubdirectoriesById(catalogId);

        //THEN
        AtomicBoolean equalsId = new AtomicBoolean(true);
        actual.forEach(e -> {
            if (e.getCatalog().getId() != catalogId) equalsId.set(false);
        });
        Assert.assertTrue(equalsId.get());
    }

    @Test
    public void createCatalog_whenDataIsCorrect_addNewCatalogToDB() {
        //GIVEN
        String title = "New Catalog";
        int upperCatalogId = 2;

        //WHEN
        int beforeSize = catalogController.getAllCatalogs().size();
        catalogController.createCatalog(title, upperCatalogId);
        int afterSize = catalogController.getAllCatalogs().size();

        //THEN
        Assert.assertEquals(beforeSize + 1, afterSize);
    }

    @Test
    public void deleteCatalog_whenCatalogExists_updateCatalogInDB() {
        //GIVEN
        int catalogId = catalogController.getAllCatalogs().get(catalogController.getAllCatalogs().size()-1).getId();

        //WHEN
        int beforeSize = catalogController.getAllCatalogs().size();
        catalogController.deleteCatalog(catalogId);
        int afterSize = catalogController.getAllCatalogs().size();

        //THEN
        Assert.assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void getCatalogById_whenCatalogExists_returnCatalogById() {
        //GIVEN
        int catalogId = 2;

        //WHEN
        Catalog catalog = catalogController.getCatalogById(catalogId);

        //THEN
        Assert.assertEquals(catalogId, catalog.getId());
    }

    @Test
    public void findCatalogByTitle_whenDataIsCorrect_returnFirstCatalogWithSuchTitle() {
        //GIVEN
        String title = "Fish";

        //WHEN
        Catalog catalog = catalogController.findCatalogByTitle(title);

        //THEN
        Assert.assertEquals(title, catalog.getTitle());
    }

}
