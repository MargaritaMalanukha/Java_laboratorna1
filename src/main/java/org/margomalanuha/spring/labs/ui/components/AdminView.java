package org.margomalanuha.spring.labs.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.margomalanuha.spring.labs.controllers.*;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.ui.MainView;

@Route(value = "admin", layout = MainView.class)
@PageTitle("Admin Panel")
public class AdminView extends HorizontalLayout {

    private Grid<User> userGrid;
    private Grid<User> adminGrid;
    private ListDataProvider<User> userDataProvider;
    private ListDataProvider<User> adminDataProvider;

    private FormLayout productUpdateLayout;
    private FormLayout catalogUpdateLayout;

    private final AdminController adminController;
    private final UserController userController;
    private final ProductController productController;
    private final CatalogController catalogController;

    private TextField catalogProductCreationColumn = new TextField("Catalog Title");
    private TextField titleProductCreationColumn = new TextField("Title");
    private TextField priceProductCreationColumn = new TextField("Price");

    private TextField catalogCatalogCreationColumn = new TextField("Upper Catalog Title");
    private TextField titleCatalogCreationColumn = new TextField("Title");

    public AdminView(AdminController adminController, UserController userController, ProductController productController,
                     CatalogController catalogController) {
        this.adminController = adminController;
        this.userController = userController;
        this.productController = productController;
        this.catalogController = catalogController;

        addClassName("admin-view");
        setHeightFull();
        add(createLayout());
    }

    private Component createLayout() {
        VerticalLayout verticalLayout = new VerticalLayout();
        createUserGrid();
        createAdminGrid();
        verticalLayout.add(new H3("Users"));
        verticalLayout.add(userGrid);
        verticalLayout.add(new H3("Admins"));
        verticalLayout.add(adminGrid);
        verticalLayout.add(new H3("Add Product"));
        verticalLayout.add(createProductForm());
        verticalLayout.add(createProductButtonLayout());
        verticalLayout.add(new H3("Add Catalog"));
        verticalLayout.add(createCatalogForm());
        verticalLayout.add(createCatalogButtonLayout());
        return verticalLayout;
    }

    private Component createCatalogForm() {
        FormLayout catalogLayout = new FormLayout();
        catalogLayout.add(catalogCatalogCreationColumn, titleCatalogCreationColumn);
        return catalogLayout;
    }

    private Component createProductForm() {
        FormLayout productLayout = new FormLayout();
        productLayout.add(titleProductCreationColumn, priceProductCreationColumn, catalogProductCreationColumn);
        return productLayout;
    }

    private Component createProductButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("create-product-button-layout");
        Button button = new Button("SAVE", buttonClickEvent -> {
            try {
                adminController.createProduct(titleProductCreationColumn.getValue(), Double.parseDouble(priceProductCreationColumn.getValue()),
                        productController.getProductByTitle(catalogProductCreationColumn.getValue()).getId());
                titleCatalogCreationColumn.setValue("");
                priceProductCreationColumn.setValue("");
                catalogProductCreationColumn.setValue("");
            } catch (Exception e){
                Notification.show("Wrong data entered. Please, check your input.");
            }
        });
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(button);
        return buttonLayout;
    }

    private Component createCatalogButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("create-catalog-button-layout");
        Button button = new Button("SAVE", buttonClickEvent -> {
            try {
                catalogController.createCatalog(titleCatalogCreationColumn.getValue(),
                        catalogController.findCatalogByTitle(catalogCatalogCreationColumn.getValue()).getId());
            } catch (Exception e) {
                Notification.show("Wrong data entered. Please, check your input.");
            }
        });
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(button);
        return buttonLayout;
    }

    private void createUserGrid() {
        userGrid = new Grid<>();
        userGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);

        userDataProvider = new ListDataProvider<>(userController.getUsersByTitle("User"));
        userGrid.setDataProvider(userDataProvider);

        userGrid.addColumn(User::getId, "id").setHeader("ID").setWidth("70px").setFlexGrow(0);
        userGrid.addColumn(User::getName).setHeader("NAME").setWidth("100px");
        userGrid.addColumn(User::getSurname).setHeader("SURNAME").setWidth("100px");
        userGrid.addColumn(User::getEmail).setHeader("EMAIL").setWidth("200px");

        userGrid.addComponentColumn(item -> createDeactivateUserButton(item.getId())).setHeader("DEACTIVATION").setWidth("170px");
        if (userController.isMainAdmin(Session.user)) {
            userGrid.addComponentColumn(item -> createUpgradeToAdminButton(item.getId())).setHeader("UPGRADE TO ADMIN").setWidth("200px");
        }
    }

    private Button createUpgradeToAdminButton(Integer id) {
        return new Button("Promote to admin", buttonClickEvent -> adminController.upgradeToAdmin(id));
    }

    private Button createDeactivateUserButton(Integer id) {
        return new Button("Deactivate", buttonClickEvent -> adminController.deactivateUser(id));
    }

    private void createAdminGrid() {
        adminGrid = new Grid<>();
        adminGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);

        adminDataProvider = new ListDataProvider<>(userController.getUsersByTitle("Admin"));
        adminGrid.setDataProvider(adminDataProvider);

        adminGrid.addColumn(User::getId, "id").setHeader("ID").setWidth("70px").setFlexGrow(0);
        adminGrid.addColumn(User::getName).setHeader("NAME").setWidth("100px");
        adminGrid.addColumn(User::getSurname).setHeader("SURNAME").setWidth("100px");
        adminGrid.addColumn(User::getEmail).setHeader("EMAIL").setWidth("200px");

        if (userController.isMainAdmin(Session.user)) {
            adminGrid.addComponentColumn(item -> createDowngradeToUser(item.getId())).setHeader("DOWNGRADE TO USER").setWidth("200px");
        }
    }

    private Button createDowngradeToUser(Integer id) {
        return new Button("Downgrade To User", buttonClickEvent -> adminController.downgradeToUser(id));
    }

}
