package org.margomalanuha.spring.labs.repository;

import org.junit.Assert;
import org.junit.Test;
import org.margomalanuha.spring.labs.models.pojo.Catalog;

public class CatalogRepositoryTest {

    public CatalogRepository catalogRepository = new CatalogRepository();

    @Test
    public void create_WhenDataIsCorrect_ShouldCreateNewRecord() {
        int prevSize = catalogRepository.getAll().size();
        catalogRepository.create(new Catalog("Крупы"));
        int size = catalogRepository.getAll().size();
        Assert.assertEquals(prevSize+1, size);
    }



}
