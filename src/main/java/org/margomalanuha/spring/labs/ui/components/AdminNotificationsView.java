package org.margomalanuha.spring.labs.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.margomalanuha.spring.labs.controllers.BasketController;
import org.margomalanuha.spring.labs.models.pojo.Purchase;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.PurchaseService;
import org.margomalanuha.spring.labs.service.UserService;
import org.margomalanuha.spring.labs.ui.MainView;

@Route(value = "admin-notifications", layout = MainView.class)
@PageTitle("Admin Notifications")
public class AdminNotificationsView extends VerticalLayout {

    private Grid<Purchase> grid = new Grid<>();

    private UserService userService;
    private PurchaseService purchaseService;

    public AdminNotificationsView(PurchaseService purchaseService, UserService userService) {
        this.purchaseService = purchaseService;
        this.userService = userService;

        addClassName("admin-notifications-view");
        setSizeFull();
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(this::createCard);
        grid.setItems(purchaseService.getAllNonFinishedPurchases());
        add(grid);
    }

    private VerticalLayout createCard(Purchase purchase) {
        User user = purchase.getUser();

        VerticalLayout layout = new VerticalLayout();
        layout.addClassName("layout");
        layout.setSpacing(false);
        layout.getThemeList().add("spacing-s");
        layout.add(new H5("User " + user.getName() + " " + user.getSurname() +
                " is waiting for your call."));
        layout.add(new H5("Number: " + user.getMobile_phone() + "."));
        layout.add(new H5("Address:" + user.getAddress() + "."));
        layout.add(new H5("Email:" + user.getEmail() + "."));
        layout.add(new H5("Cheque: " + purchase.getCheque()));
        Button button = new Button("ACCEPTED");
        button.addClickListener(event -> {
            purchaseService.setPurchaseAsFinished(purchase);
        });
        layout.add(button);
        return layout;
    }

}
