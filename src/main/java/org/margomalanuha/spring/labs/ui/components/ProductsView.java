package org.margomalanuha.spring.labs.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.margomalanuha.spring.labs.controllers.*;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.service.ProductsService;
import org.margomalanuha.spring.labs.service.PurchaseService;
import org.margomalanuha.spring.labs.service.UserService;
import org.margomalanuha.spring.labs.ui.MainView;

@Route(value = "products", layout = MainView.class)
@PageTitle("All products")
public class ProductsView extends Div {

    private Grid <Product> grid;
    private ListDataProvider<Product> dataProvider;
    private ProductsService productsService;
    private PurchaseService purchaseService;
    private UserService userService;

    private Grid.Column<Product> idColumn;
    private Grid.Column<Product> titleColumn;
    private Grid.Column<Product> priceColumn;

    public ProductsView(ProductsService productsService, PurchaseService purchaseService, UserService userService) {
        this.productsService = productsService;
        this.purchaseService = purchaseService;
        this.userService = userService;

        addClassName("products-view");
        setSizeFull();
        createGrid();
        add(grid);
    }

    private void createGrid() {
        createGridComponent();
        addColumnsToGrid();
        grid.addComponentColumn(this::createAddToBasketButton).setHeader("ACTIONS");
        if (userService.isAdmin(Session.user)) {
            grid.addComponentColumn(this::createDeleteProductButton).setHeader("DELETION");
        }
    }

    private void createGridComponent() {
        grid = new Grid<>();
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("100%");

        dataProvider = new ListDataProvider<>(productsService.getAllProducts());
        grid.setDataProvider(dataProvider);
    }

    private Button createAddToBasketButton(Product product) {
        return new Button("Add To Basket", buttonClickEvent -> {
            purchaseService.addToBasket(product.getId(), Session.user.getId());
        });
    }

    private Button createDeleteProductButton(Product product) {
        return new Button("Delete Product", buttonClickEvent -> productsService.deleteProduct(product.getId()));
    }

    private void addColumnsToGrid() {
        createIdColumn();
        createTitleColumn();
        createPriceColumn();
    }

    private void createIdColumn() {
        idColumn = grid.addColumn(Product::getId, "id").setHeader("ID").setWidth("120px").setFlexGrow(0);
    }

    private void createTitleColumn() {
        titleColumn = grid.addColumn(Product::getTitle).setHeader("TITLE").setWidth("140px");
    }

    private void createPriceColumn() {
        priceColumn = grid.addColumn(Product::getPrice).setHeader("PRICE").setWidth("140px");
    }
};
