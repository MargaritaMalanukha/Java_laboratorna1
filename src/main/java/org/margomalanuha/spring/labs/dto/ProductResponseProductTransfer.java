package org.margomalanuha.spring.labs.dto;

import org.margomalanuha.spring.labs.models.pojo.Product;

public class ProductResponseProductTransfer {

    public static ProductResponse to(Product p) {
        return new ProductResponse(p.getId(), p.getTitle(), p.getPrice(), p.getCatalog());
    }

    public static Product from(ProductResponse pr) {
        Product p = new Product();
        p.setId(pr.getId());
        p.setTitle(pr.getTitle());
        p.setPrice(pr.getPrice());
        p.setCatalog(pr.getCatalog());
        return p;
    }

}
