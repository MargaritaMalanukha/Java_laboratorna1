package org.margomalanuha.spring.labs.ui.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.*;
import org.margomalanuha.spring.labs.controllers.BasketController;
import org.margomalanuha.spring.labs.controllers.CatalogController;
import org.margomalanuha.spring.labs.controllers.ProductController;
import org.margomalanuha.spring.labs.controllers.Session;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.service.CatalogService;
import org.margomalanuha.spring.labs.service.ProductsService;
import org.margomalanuha.spring.labs.service.PurchaseService;
import org.margomalanuha.spring.labs.ui.MainView;

@Route(value = "catalogs", layout = MainView.class)
@PageTitle("Catalogs")
public class CatalogsView extends HorizontalLayout implements HasUrlParameter<Integer> {

    private CatalogService catalogService;
    private ProductsService productsService;
    private PurchaseService purchaseService;

    private int currentCatalogId;
    private ListDataProvider<Catalog> catalogListDataProvider;
    private ListDataProvider<Product> productListDataProvider;
    private Grid<Catalog> catalogGrid;
    private Grid<Product> productGrid;

    private Grid.Column <Catalog> titleCatalogColumn;

    private Grid.Column <Product> idProductColumn;
    private Grid.Column <Product> titleProductColumn;
    private Grid.Column <Product> priceProductColumn;

    public CatalogsView(CatalogService catalogService, ProductsService productsService, PurchaseService purchaseService) {
        this.catalogService = catalogService;
        this.purchaseService = purchaseService;
        this.productsService = productsService;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter Integer integer) {
        if (integer == null || integer <= 0) currentCatalogId = 1;
        else currentCatalogId = integer;
        addClassName("catalogs-view");
        setSizeFull();
        programLogic();
    }

    public void programLogic() {
        add(createSubcatalogsVerticalLayout());
        add(createProductsVerticalLayout());
    }

    private VerticalLayout createSubcatalogsVerticalLayout() {
        VerticalLayout catalogLayout = new VerticalLayout();
        catalogLayout.setWidth("40%");
        String title = catalogService.getCatalogById(currentCatalogId).getTitle();
        catalogLayout.add(new H3("Subcatalogs in catalog '" + title + "' : "));
        createCatalogGrid();
        catalogLayout.add(catalogGrid);
        return catalogLayout;
    }

    private void createCatalogGrid() {
        catalogGrid = new Grid<>();
        catalogGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        catalogGrid.setHeight("100%");

        catalogListDataProvider = new ListDataProvider<>(catalogService.getSubdirectoriesById(currentCatalogId));
        catalogGrid.setDataProvider(catalogListDataProvider);

        titleCatalogColumn = catalogGrid.addColumn(Catalog::getTitle, "id").setHeader("TITLE").setWidth("150px").setFlexGrow(0);

        catalogGrid.addComponentColumn(item -> createMoveToCatalogButton(item.getId())).setHeader("ACTIONS");

    }

    private Button createMoveToCatalogButton(Integer id) {
        return new Button("Move to catalogue", buttonClickEvent -> {
            UI.getCurrent().navigate("catalogs/" + id);
        });
    }

    private VerticalLayout createProductsVerticalLayout() {
        VerticalLayout productLayout = new VerticalLayout();
        productLayout.add(new H3("Products in this catalog: "));
        createProductGrid();
        productLayout.add(productGrid);
        return productLayout;
    }

    private void createProductGrid() {
        productGrid = new Grid<>();
        productGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        productGrid.setHeight("100%");

        productListDataProvider = new ListDataProvider<>(productsService.getProductsByCatalog(currentCatalogId));
        productGrid.setDataProvider(productListDataProvider);

        idProductColumn = productGrid.addColumn(Product::getId, "id").setHeader("ID").setWidth("70px").setFlexGrow(0);
        titleProductColumn = productGrid.addColumn(Product::getTitle).setHeader("TITLE").setWidth("150px");
        priceProductColumn = productGrid.addColumn(Product::getPrice).setHeader("PRICE").setWidth("100px");

        productGrid.addComponentColumn(this::createAddToBasketButton).setHeader("ACTIONS");
    }

    private Button createAddToBasketButton(Product product) {
        return new Button("Add To Basket", buttonClickEvent -> {
            purchaseService.addToBasket(product.getId(), Session.user.getId());
        });
    }


}
