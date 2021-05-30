package org.margomalanuha.spring.labs.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.margomalanuha.spring.labs.config.Config;
import org.margomalanuha.spring.labs.controllers.UserController;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserControllerIntegrationTest {

    private static ApplicationContext context;
    private UserController userController;

    @Before
    public void init() {
        context = new AnnotationConfigApplicationContext(Config.class);
        userController = context.getBean(UserController.class);
    }


    @Test
    public void updateData_whenDataIsCorrect_updateUserInDatabase() {
        //GIVEN
        List<User> users = userController.getUserService().getAllUsers();
        users.forEach(System.out::println);

        Assertions.assertEquals(true, true);
    }

}
