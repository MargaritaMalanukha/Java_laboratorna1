package org.margomalanuha.spring.labs.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.margomalanuha.spring.labs.config.Config;
import org.margomalanuha.spring.labs.controllers.UserController;
import org.margomalanuha.spring.labs.controllers.exceptions.RegistrationException;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.UserServiceImpl;
import org.margomalanuha.spring.labs.service.exceptions.WrongEmailOrPasswordException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration( loader = AnnotationConfigWebContextLoader.class, classes = {Config.class})
@SpringBootTest(classes = UserControllerIntegrationTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = "spring.autoconfigure.exclude=com.vaadin.flow.spring.SpringBootAutoConfiguration")
@Transactional
public class UserControllerIntegrationTest {

   /* private static ApplicationContext context;
    private UserController userController;

    @Before
    public void init() {
        context = new AnnotationConfigApplicationContext(Config.class);
        userController = context.getBean(UserController.class);
    }

    @Test
    public void register_whenDataIsCorrect_addUserToDatabase() {
        //GIVEN
        User user = new User("Alexander", "Stepanov", "test117@gmail.com", "qwerty");

        //WHEN
        User user1 = userController.register(user);

        //THEN
        List<User> allUsers = new LinkedList<>(userController.getAllUsers());
        Assertions.assertEquals(user1.getEmail(), allUsers.get(allUsers.size()-1).getEmail());

    }

    @Test(expected = RegistrationException.class)
    public void register_whenNoDataFound_throwRegistrationException() {
        //GIVEN
        User user = new User();

        //WHEN
        User user1 = userController.register(user);

    }

    @Test(expected = RegistrationException.class)
    public void register_whenEmailIsAlreadyUsed_throwRegistrationException() {
        //GIVEN
        User user = new User("Margarita", "Malanukha", "margomalanuha@gmail.com", "rita");

        //WHEN
        User user1 = userController.register(user);

    }

    @Test
    public void login_whenDataIsCorrect_returnNewUser() {
        //GIVEN
        String email = "margomalanuha@gmail.com";
        String password = "rita";

        //WHEN
        User user = userController.login(email, password);

        //THEN
        Assertions.assertEquals(email, user.getEmail());
    }

    @Test(expected = WrongEmailOrPasswordException.class)
    public void login_whenDataIsNotCorrect_throwWrongEmailOrPasswordException() {
        //GIVEN
        String email = "margom@gmail.com";
        String password = "rita";

        //WHEN
        User user = userController.login(email, password);
    }

    @Test
    public void updateData_whenDataIsCorrect_updateUserInDatabase() {
        //GIVEN

    }*/

}
