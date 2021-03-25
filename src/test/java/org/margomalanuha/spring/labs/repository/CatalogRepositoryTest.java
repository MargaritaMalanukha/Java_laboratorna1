package org.margomalanuha.spring.labs.repository;

import org.junit.Assert;
import org.junit.Test;
import org.margomalanuha.spring.labs.config.Config;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional

public class CatalogRepositoryTest {

    public CatalogRepository catalogRepository = new CatalogRepository();

    @Test
    public void create_WhenDataIsCorrect_ShouldCreateNewRecord() {
        int prevSize = catalogRepository.getAll().size();
        catalogRepository.create(new Catalog("Candies", 5));
        int size = catalogRepository.getAll().size();
        Assert.assertEquals(prevSize+1, size);
    }

    @Test
    public void update_WhenDataIsCorrect_ShouldUpdateRecord() {
        catalogRepository.update(new Catalog(11, "NewTitle", 0));
        Catalog catalog = catalogRepository.getById(11);
        Assert.assertEquals(new Catalog(11, "NewTitle", 0), catalog);
    }

    @Test
    public void getById_WhenDataIsCorrect_ShouldReturnRecord() {
        Catalog catalog = catalogRepository.getById(1);
        Assert.assertEquals(new Catalog(1, "Fish", 0), catalog);
    }

    @Test
    public void delete_WhenDataIsCorrect_ShouldDeleteRecord() {

    }



}
