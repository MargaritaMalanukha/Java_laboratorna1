package org.margomalanuha.spring.labs.ui.components;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.margomalanuha.spring.labs.controllers.BasketController;
import org.margomalanuha.spring.labs.models.pojo.Purchase;
import org.margomalanuha.spring.labs.service.PurchaseService;
import org.margomalanuha.spring.labs.ui.MainView;

@Route(value = "admin-notifications", layout = MainView.class)
@PageTitle("Admin Notifications")
public class AdminNotificationsView extends VerticalLayout {

    private Grid<Purchase> grid = new Grid<>();

    public AdminNotificationsView(PurchaseService purchaseService) {
        addClassName("admin-notifications-view");
        setSizeFull();
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
      //  grid.addComponentColumn(basketController.getAllNonFinishedPurchases());


    }

}
