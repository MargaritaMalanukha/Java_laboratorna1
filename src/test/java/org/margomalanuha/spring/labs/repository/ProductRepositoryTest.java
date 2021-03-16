package org.margomalanuha.spring.labs.repository;

import org.junit.Assert;
import org.junit.Test;
import org.margomalanuha.spring.labs.models.pojo.Product;

import java.util.List;

public class ProductRepositoryTest {

    public ProductRepository productRepository = new ProductRepository();

    @Test
    public void create_WhenDataIsCorrect_ShouldCreateNewRecord() {
        int prevSize = productRepository.getAll().size();
        productRepository.create(new Product("Banana", 25.05, 2));
        int size = productRepository.getAll().size();
        Assert.assertEquals(prevSize+1, size);
    }

    @Test
    public void update_WhenDataIsCorrect_ShouldUpdateRecord() {
        List<Product> list = productRepository.getAll();
        productRepository.update(new Product(list.size(), "Banana", 27.00, 2));
        Assert.assertEquals(27, list.get(list.size()-1).getPrice(), 0.001);
    }

    @Test
    public void delete_WhenDataIsCorrect_ShouldDeleteRecord() {
        int prevSize = productRepository.getAll().size();
        productRepository.delete(prevSize);
        int size = productRepository.getAll().size();
        Assert.assertEquals(prevSize-1, size);
    }


}
