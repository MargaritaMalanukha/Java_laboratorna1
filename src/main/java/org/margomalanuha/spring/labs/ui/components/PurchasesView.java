package org.margomalanuha.spring.labs.ui.components;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.margomalanuha.spring.labs.controllers.BasketController;
import org.margomalanuha.spring.labs.controllers.Session;
import org.margomalanuha.spring.labs.models.pojo.Purchase;
import org.margomalanuha.spring.labs.service.PurchaseService;
import org.margomalanuha.spring.labs.ui.MainView;

@Route(value = "purchases", layout = MainView.class)
@PageTitle("My Purchases")
public class PurchasesView extends VerticalLayout {

    private Grid<Purchase> grid;
    private ListDataProvider<Purchase> dataProvider;

    private PurchaseService purchaseService;

    public PurchasesView(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;

        addClassName("purchases-view");
        setSizeFull();
        createGrid();
        add(new H3("Purchases"));
        add(grid);
    }

    private void createGrid() {
        grid = new Grid<>();
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("100%");
        grid.setWidth("70%");

        dataProvider = new ListDataProvider<>(purchaseService.getPurchaseHistory(Session.user.getId()));
        grid.setDataProvider(dataProvider);

        grid.addColumn(Purchase::getId, "id").setHeader("ID").setWidth("70px").setFlexGrow(0);
        grid.addColumn(Purchase::getPrice).setHeader("PRICE");
        grid.addColumn(Purchase::getTime).setHeader("TIME");
    }

}
