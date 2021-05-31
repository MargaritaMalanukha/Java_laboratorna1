package org.margomalanuha.spring.labs.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.margomalanuha.spring.labs.controllers.BasketController;
import org.margomalanuha.spring.labs.controllers.Session;
import org.margomalanuha.spring.labs.controllers.UserController;
import org.margomalanuha.spring.labs.models.pojo.Purchase;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.PurchaseService;
import org.margomalanuha.spring.labs.service.UserService;
import org.margomalanuha.spring.labs.ui.MainView;

@Route(value = "basket/checkout", layout = MainView.class)
@PageTitle("Checkout")
public class CheckoutView extends VerticalLayout {

    private TextField firstName = new TextField("First name");
    private EmailField email = new EmailField("Email");
    private TextField mobilePhone = new TextField("Mobile Phone");
    private TextField address = new TextField("Address");

    private Button buy = new Button("BUY");
    private Button cancel = new Button("GO BACK");

    private User user;

    public CheckoutView(UserService userService, PurchaseService purchaseService) {
        addClassName("checkout-view");
        setHeightFull();
        user = Session.user;

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        cancel.addClickListener(e -> UI.getCurrent().navigate("basket"));
        buy.addClickListener(e -> {
            user.setMobile_phone(mobilePhone.getValue());
            user.setAddress(address.getValue());
            userService.updateData(user);
            Purchase purchase = purchaseService.addPurchaseToHistory(user.getId());
            purchaseService.clearBasket(user.getId());
            UI.getCurrent().navigate("products");
            Notification.show("Your purchase has been completed! Our manager will contact you in 5 minutes!");
            Notification.show("Your cheque is: " + purchase.getCheque());
        });
    }
    private Component createTitle() {
        return new H3("Enter your personal information:");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        firstName.setValue(user.getName() == null ? "" : user.getName());
        email.setValue(user.getEmail() == null ? "" : user.getEmail());
        mobilePhone.setValue(user.getMobile_phone() == null ? "" : user.getMobile_phone());
        address.setValue(user.getAddress() == null ? "" : user.getAddress());
        formLayout.add(firstName,  email, mobilePhone, address);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        buy.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(buy);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

}
