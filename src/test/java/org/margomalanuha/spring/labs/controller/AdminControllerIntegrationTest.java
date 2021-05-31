package org.margomalanuha.spring.labs.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.margomalanuha.spring.labs.config.Config;
import org.margomalanuha.spring.labs.controllers.AdminController;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.repository.UserRepository;
import org.margomalanuha.spring.labs.repository.UserTypeRepository;
import org.margomalanuha.spring.labs.service.UserService;
import org.margomalanuha.spring.labs.service.UserServiceImpl;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SpringBootTest(classes = {Config.class})
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class AdminControllerIntegrationTest {

    private AdminController adminController;
    private UserRepository userRepository;

    @Before
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        userRepository = context.getBean(UserRepository.class);
        UserTypeRepository userTypeRepository = context.getBean(UserTypeRepository.class);
        UserService userService = new UserServiceImpl(userRepository, userTypeRepository);

        adminController = new AdminController(userService);
    }

    @After
    public void tearDown(){
        User user = userRepository.findAll().get(userRepository.findAll().size()-1);
        user.setActive(true);
    }

    @Test
    public void deactivateUser_setIsActiveFalseForThisUserAndUpdateDB() {
        //GIVEN
        User user = userRepository.findAll().get(userRepository.findAll().size()-1);

        //WHEN
        adminController.deactivateUser(user.getId());

        //THEN
        User actual = userRepository.findAll().get(userRepository.findAll().size()-1);
        Assert.assertFalse(actual.isActive());

    }

    @Test
    public void upgradeToAdmin_setUserTypeAdminAndUpdateDB() {
        //GIVEN
        User user = userRepository.findAll().get(userRepository.findAll().size()-1);

        //WHEN
        adminController.upgradeToAdmin(user.getId());

        //THEN
        User actual = userRepository.findAll().get(userRepository.findAll().size()-1);
        Assert.assertEquals("Admin", actual.getUserType().getTitle());
    }

    @Test
    public void downgradeToUser_setUserTypeUserAndUpdateDB() {
        //GIVEN
        User user = userRepository.findAll().get(userRepository.findAll().size()-1);

        //WHEN
        adminController.downgradeToUser(user.getId());

        //THEN
        User actual = userRepository.findAll().get(userRepository.findAll().size()-1);
        Assert.assertEquals("User", actual.getUserType().getTitle());
    }






}
