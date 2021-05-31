package org.margomalanuha.spring.labs.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.margomalanuha.spring.labs.controllers.BasketController;
import org.margomalanuha.spring.labs.controllers.ProductController;
import org.margomalanuha.spring.labs.controllers.Session;
import org.margomalanuha.spring.labs.models.pojo.BasketItem;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.service.ProductsService;
import org.margomalanuha.spring.labs.service.PurchaseService;
import org.margomalanuha.spring.labs.ui.MainView;

@Route(value = "basket", layout = MainView.class)
@PageTitle("Basket")
public class BasketView extends Div {

    private Grid<Product> grid;
    private ListDataProvider<Product> dataProvider;
    private PurchaseService purchaseService;

    private Grid.Column<Product> idColumn;
    private Grid.Column<Product> titleColumn;
    private Grid.Column<Product> priceColumn;

    public BasketView(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;

        addClassName("basket-view");
        setSizeFull();
        createGrid();
        add(grid);
        add(createButtonLayout());
    }

    private void createGrid() {
        createGridComponent();
        addColumnsToGrid();
        grid.addComponentColumn(item -> createDeleteFromBasketButton(item, grid)).setHeader("ACTIONS");
    }

    private void createGridComponent() {
        grid = new Grid<>();
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);

        dataProvider = new ListDataProvider<>(purchaseService.getBasket(Session.user.getId()));
        grid.setDataProvider(dataProvider);
    }

    private Button createDeleteFromBasketButton(Product product, Grid<Product> grid) {
        return new Button("Delete", buttonClickEvent -> {
            purchaseService.deleteFromBasket(product.getId(), Session.user.getId());
            grid.getDataProvider().refreshAll();
        });
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        buttonLayout.setWidth("100%");

        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        Button checkout = new Button("Checkout", buttonClickEvent -> {
            UI.getCurrent().navigate("basket/checkout");
        });
        checkout.setHeight("40px");
        checkout.setWidth("140px");
        checkout.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(checkout);
        return buttonLayout;
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
        titleColumn = grid.addColumn(Product::getTitle).setHeader("TITLE").setWidth("200px");
    }

    private void createPriceColumn() {
        priceColumn = grid.addColumn(Product::getPrice).setHeader("PRICE").setWidth("200px");
    }

}
