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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;
    private UserServiceImpl userService;
    private List<UserType> userTypes;

    @Before
    public void init() {
        userRepository = Mockito.mock(UserRepository.class);
        userTypeRepository = Mockito.mock(UserTypeRepository.class);
        userService = new UserServiceImpl(userRepository, userTypeRepository);

        userTypes = new LinkedList<>();
        userTypes.add(new UserType(1, "User", new LinkedList<>()));
    }

    @Test
    public void register_whenDataIsValid_addUser() {
        //GIVEN
        String name = "Alexander";
        String surname = "Petrikovich";
        String email = "sashapch@gmail.com";
        String password = "sanyura2000";
        Mockito.doReturn(userTypes).when(userTypeRepository).findAll();
        User user = new User(name, surname, email, password, userTypes.get(0));
        Mockito.doReturn(user).when(userRepository).save(user);

        //WHEN
        userService.register(user);

        //THEN
        Mockito.verify(userRepository).save(user);
    }

    @Test
    public void updateData_whenNewDataIsValid_updateDatabase() {
        //GIVEN

        String name = "Alexander";
        String surname = "Petrikovich";
        String email = "sashapch@gmail.com";
        String password = "sanyura2000";
        Mockito.doReturn(userTypes).when(userTypeRepository).findAll();
        User user = new User(name, surname, email, password, userTypes.get(0));
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
        user.setId(12);
        Mockito.doReturn(Optional.of(user)).when(userRepository).findById(user.getId());
        user.setActive(false);
        Mockito.doReturn(user).when(userRepository).save(user);

        //WHEN
        userService.deactivateUser(user.getId());

        //THEN
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void getAllUsers_ifAnyActiveUsersPresent_returnAllActiveUsers() {
        //GIVEN
        List<User> users = new LinkedList<>();
        User user = new User();
        user.setId(13);
        user.setActive(true);
        User user1 = new User();
        user1.setId(14);
        user1.setActive(true);
        users.add(new User());
        users.add(user);
        users.add(user1);
        users.add(new User());
        Mockito.doReturn(users).when(userRepository).findAll();

        //WHEN
        List<User> actual = userService.getAllUsers();

        //THEN
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        users.remove(3);
        users.remove(0);

        Assertions.assertEquals(users, actual);
    }

    @Test
    public void getAllUserTypes_ifUserTypesAreInDatabase_returnAllUserTypes() {
        //GIVEN
        List<UserType> userTypeList = new LinkedList<>();
        userTypes.add(userTypes.get(0));
        userTypes.add(new UserType());
        userTypes.add(new UserType(2, "Admin", new LinkedList<>()));
        Mockito.doReturn(userTypeList).when(userTypeRepository).findAll();

        //WHEN
        List<UserType> actual = userService.getAllUserTypes();

        //THEN
        Mockito.verify(userTypeRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(actual, userTypeList);
    }

    @Test
    public void getUsersByTitle_ifUserTypeTitleEqualsIgnoreCaseAndAreActive_returnUsers() {
        //GIVEN
        List<User> users = new LinkedList<>();
        User user = new User();
        user.setUserType(userTypes.get(0));
        user.setActive(true);
        User user1 = new User();
        user1.setUserType(userTypes.get(0));
        User user2 = new User();
        user2.setUserType(new UserType(2, "Admin", new LinkedList<>()));
        users.add(user);
        users.add(user1);
        users.add(user2);
        Mockito.doReturn(users).when(userRepository).findAll();

        //WHEN
        List<User> actual = userService.getUsersByTitle("User");

        //THEN
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        users.remove(2);
        users.remove(1);
        Assertions.assertEquals(users, actual);
    }


}
