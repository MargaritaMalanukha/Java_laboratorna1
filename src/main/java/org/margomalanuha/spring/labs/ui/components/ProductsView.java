package org.margomalanuha.spring.labs.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.margomalanuha.spring.labs.controllers.*;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.service.ProductsService;
import org.margomalanuha.spring.labs.ui.AuthMainView;
import org.margomalanuha.spring.labs.ui.MainView;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Route(value = "products", layout = MainView.class)
@PageTitle("All products")
public class ProductsView extends Div {

    private Grid <Product> grid;
    private ListDataProvider<Product> dataProvider;
    private ProductController productController;
    private BasketController basketController;
    private UserController userController;
    private AdminController adminController;

    private Grid.Column<Product> idColumn;
    private Grid.Column<Product> titleColumn;
    private Grid.Column<Product> priceColumn;

    public ProductsView(ProductController productController, BasketController basketController, UserController userController,
                        AdminController adminController) {
        this.productController = productController;
        this.basketController = basketController;
        this.userController = userController;
        this.adminController = adminController;
        addClassName("products-view");
        setSizeFull();
        createGrid();
        add(grid);
    }

    private void createGrid() {
        createGridComponent();
        addColumnsToGrid();
        grid.addComponentColumn(this::createAddToBasketButton).setHeader("ACTIONS");
        if (userController.isAdmin(Session.user)) {
            grid.addComponentColumn(this::createDeleteProductButton).setHeader("DELETION");
        }
    }

    private void createGridComponent() {
        grid = new Grid<>();
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("100%");

        dataProvider = new ListDataProvider<>(productController.getAllProducts());
        grid.setDataProvider(dataProvider);
    }

    private Button createAddToBasketButton(Product product) {
        return new Button("Add To Basket", buttonClickEvent -> {
            basketController.addToBasket(product.getId(), Session.user.getId());
        });
    }

    private Button createDeleteProductButton(Product product) {
        return new Button("Delete Product", buttonClickEvent -> adminController.deleteProduct(product));
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
