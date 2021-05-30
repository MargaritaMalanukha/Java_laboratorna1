package org.margomalanuha.spring.labs.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
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
import org.margomalanuha.spring.labs.controllers.UserController;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.UserService;
import org.margomalanuha.spring.labs.ui.AuthMainView;
import org.margomalanuha.spring.labs.ui.MainView;

@Route(value = "register", layout = AuthMainView.class)
@PageTitle("Registration")
public class RegisterView extends VerticalLayout {

    private TextField name = new TextField("First name");
    private TextField surname = new TextField("Last name");
    private EmailField email = new EmailField("Email address");
    private PasswordField password = new PasswordField("Password");

    private Button login = new Button("Go To Login");
    private Button register = new Button("Register");

    private Binder<User> binder = new Binder(User.class);

    public RegisterView(UserController userController) {
        addClassName("register-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        login.addClickListener(e -> UI.getCurrent().navigate("login"));
        register.addClickListener(e -> {
            userController.login(email.getValue(), password.getValue());
            clearForm();
            UI.getCurrent().navigate("products");
        });
    }

    private void clearForm() {
        binder.setBean(new User());
    }

    private Component createTitle() {
        return new H3("Register");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(name, surname, email, password);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        register.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(register);
        buttonLayout.add(login);
        return buttonLayout;
    }

}
