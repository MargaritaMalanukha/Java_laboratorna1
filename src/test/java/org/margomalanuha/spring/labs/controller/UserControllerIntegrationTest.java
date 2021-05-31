package org.margomalanuha.spring.labs.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.margomalanuha.spring.labs.config.Config;
import org.margomalanuha.spring.labs.config.TestConfig;
import org.margomalanuha.spring.labs.controllers.Session;
import org.margomalanuha.spring.labs.controllers.UserController;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.models.pojo.UserType;
import org.margomalanuha.spring.labs.repository.UserRepository;
import org.margomalanuha.spring.labs.repository.UserTypeRepository;
import org.margomalanuha.spring.labs.service.UserService;
import org.margomalanuha.spring.labs.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SpringBootTest(classes = {Config.class})
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class UserControllerIntegrationTest {

    private UserController userController;

    @Before
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        UserRepository userRepository = context.getBean(UserRepository.class);
        UserTypeRepository userTypeRepository = context.getBean(UserTypeRepository.class);
        UserService userService = new UserServiceImpl(userRepository, userTypeRepository);
        userController = new UserController(userService);
    }

    @Test
    public void login_whenDataIsCorrect_returnUserAndAuthorize() {
        //GIVEN
        String email = "admin";
        String password = "admin";

        //WHEN
        userController.login(email, password);

        //THEN
        Assert.assertEquals(email, Session.user.getEmail());
    }

    @Test
    public void isMainAdmin_whenUserIsMainAdmin_returnTrue() {
        //GIVEN
        User user = new User();
        user.setUserType(new UserType(2, "Main Admin", new LinkedList<>()));

        //WHEN
        boolean isMainAdmin = userController.isMainAdmin(user);

        //THEN
        Assert.assertTrue(isMainAdmin);
    }

    @Test
    public void isAdmin_whenUserIsAdminOrMainAdmin_returnTrue() {
        //GIVEN
        User user = new User();
        user.setUserType(new UserType(2, "Main Admin", new LinkedList<>()));
        User user1 = new User();
        user1.setUserType(new UserType(3, "Admin", new LinkedList<>()));

        //WHEN
        boolean isAdmin = userController.isAdmin(user);
        boolean isAdmin1 = userController.isAdmin(user1);

        //THEN
        Assert.assertTrue(isAdmin);
        Assert.assertTrue(isAdmin1);
    }

    @Test
    public void register_whenDataIsCorrect_addNewUserToDB() {
        //GIVEN
        User user = new User();
        user.setName("Sergey");
        user.setSurname("Gryshkov");
        user.setEmail("testtt124" + System.currentTimeMillis() + "@gmail.com");
        user.setPassword("veryStrongPassword");
        user.setActive(true);

        //WHEN
        int beforeSize = userController.getAllUsers().size();
        userController.register(user);
        int afterSize = userController.getAllUsers().size();

        //THEN
        Assert.assertEquals(beforeSize + 1, afterSize);
    }


}
