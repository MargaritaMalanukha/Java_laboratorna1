package org.margomalanuha.spring.labs.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.repository.UserRepository;
import org.margomalanuha.spring.labs.repository.tools.Repository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private UserRepository repository;
    private UserServiceImpl userService;

    @Before
    public void init() {
        repository = Mockito.mock(UserRepository.class);
        repository.setTest();
        userService = new UserServiceImpl(repository);
    }

    @Test
    public void register_whenDataIsValid_addUser() {
        //GIVEN
        String name = "Alexander";
        String surname = "Petrikovich";
        String email = "sashapch@gmail.com";
        String password = "sanyura2000";
        User user = new User(name, surname, email, password);
        Mockito.doReturn(1).when(repository).create(user);

        //WHEN
        User actual = userService.register(user);

        //THEN
        Mockito.verify(repository).create(user);
        Assertions.assertEquals(user, actual);
    }

    @Test
    public void updateData_whenNewDataIsValid_updateDatabase() {
        //GIVEN
        String name = "Alexander";
        String surname = "Petrikovich";
        String email = "sashapch@gmail.com";
        String password = "sanyura2000";
        User user = new User(name, surname, email, password);
        Mockito.doReturn(1).when(repository).update(user);

        //WHEN
        userService.updateData(user);

        //THEN
        Mockito.verify(repository).update(user);
    }

    @Test
    public void deactivateUser_whenUser_deactivateUserInDatabase() {
        //GIVEN
        User user = new User();
        Mockito.doReturn(1).when(repository).update(user);

        //WHEN
        userService.deactivateUser(user);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).update(user);
    }





}
