package org.margomalanuha.spring.labs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.margomalanuha.spring.labs.models.pojo.Catalog;

@Data
@AllArgsConstructor
public class ProductResponse {

    private int id;
    private String title;
    private double price;
    private Catalog catalog;

}
