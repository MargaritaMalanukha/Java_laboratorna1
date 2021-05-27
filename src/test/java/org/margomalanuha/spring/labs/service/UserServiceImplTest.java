package org.margomalanuha.spring.labs.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.models.pojo.UserType;
import org.margomalanuha.spring.labs.repository.UserRepository;
import org.margomalanuha.spring.labs.repository.UserTypeRepository;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

  /*  private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;
    private UserServiceImpl userService;
    private List<UserType> userTypes;

    @Before
    public void init() {
        userRepository = Mockito.mock(UserRepository.class);
        userTypeRepository = Mockito.mock(UserTypeRepository.class);
        userService = new UserServiceImpl(userRepository, userTypeRepository);

        userTypes = new LinkedList<>();
        userTypes.add(new UserType(1, "User"));
    }

    @Test
    public void register_whenDataIsValid_addUser() {
        //GIVEN
        String name = "Alexander";
        String surname = "Petrikovich";
        String email = "sashapch@gmail.com";
        String password = "sanyura2000";
        Mockito.doReturn(userTypes).when(userTypeRepository).findAll();
        User user = new User(name, surname, email, password);
        Mockito.doReturn(user).when(userRepository).save(user);

        //WHEN
        User actual = userService.register(user);

        //THEN
        Mockito.verify(userRepository).save(user);
        Assertions.assertEquals(user, actual);
    }

    @Test
    public void updateData_whenNewDataIsValid_updateDatabase() {
        //GIVEN

        String name = "Alexander";
        String surname = "Petrikovich";
        String email = "sashapch@gmail.com";
        String password = "sanyura2000";
        Mockito.doReturn(userTypes).when(userTypeRepository).findAll();
        User user = new User(name, surname, email, password);
        Mockito.doReturn(user).when(userRepository).save(user);

        //WHEN
        userService.updateData(user);

        //THEN
        Mockito.verify(userRepository).save(user);
    }

    @Test
    public void deactivateUser_whenUser_deactivateUserInDatabase() {
        //GIVEN
        User user = new User();
        Mockito.doReturn(user).when(userRepository).save(user);

        //WHEN
        userService.deactivateUser(user);

        //THEN
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
*/




}
