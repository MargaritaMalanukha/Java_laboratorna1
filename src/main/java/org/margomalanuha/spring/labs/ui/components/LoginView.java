package org.margomalanuha.spring.labs.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.margomalanuha.spring.labs.controllers.Session;
import org.margomalanuha.spring.labs.controllers.UserController;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.UserService;
import org.margomalanuha.spring.labs.ui.AuthMainView;
import org.margomalanuha.spring.labs.ui.MainView;

@Route(value = "login", layout = AuthMainView.class)
@PageTitle("Login")
public class LoginView extends VerticalLayout {

    private EmailField email = new EmailField("Email address");
    private PasswordField password = new PasswordField("Password");

    private Button login = new Button("Login");
    private Button register = new Button("Go To Registration");

    public LoginView(UserService userService) {
        Session.user = new User();
        addClassName("register-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        add();

        clearForm();

        register.addClickListener(e -> UI.getCurrent().navigate("register"));
        login.addClickListener(e -> {
            userService.login(email.getValue(), password.getValue());
            clearForm();
            UI.getCurrent().navigate("products");
        });
    }

    private void clearForm() {
        email.setValue("");
        password.setValue("");
    }

    private Component createTitle() {
        return new H3("Login");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(email, password);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        login.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(login);
        buttonLayout.add(register);
        return buttonLayout;
    }

}
