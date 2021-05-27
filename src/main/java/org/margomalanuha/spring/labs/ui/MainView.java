package org.margomalanuha.spring.labs.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.repository.ProductRepository;
import org.margomalanuha.spring.labs.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class MainView extends VerticalLayout {

    private final ProductsService productsService;
    final Grid<Product> grid;

    @Autowired
    public MainView(ProductsService productsService) {
        this.productsService = productsService;
        this.grid = new Grid<>(Product.class);
        add(grid);
        grid.setItems(productsService.getAllProducts());
    }



}
