package org.margomalanuha.spring.labs.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.controllers.Session;
import org.margomalanuha.spring.labs.service.exceptions.LoginException;
import org.margomalanuha.spring.labs.service.exceptions.RegistrationException;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.models.pojo.UserType;
import org.margomalanuha.spring.labs.repository.UserRepository;
import org.margomalanuha.spring.labs.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Getter
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserTypeRepository(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public void register(User user) {
        if (user.getName() == null || user.getSurname() == null || user.getEmail() == null || user.getPassword() == null) {
            throw new RegistrationException("Wrong data for registration!");
        }
        if (userRepository.findAll().stream().anyMatch(e -> e.getEmail().equals(user.getEmail()))) {
            throw new RegistrationException("User with this email already exists!");
        }
        UserType userType = userTypeRepository.findAll().stream()
                .filter(e -> e.getTitle().equalsIgnoreCase("User")).findFirst().orElseThrow(NoSuchElementException::new);
        user.setUserType(userType);
        userRepository.save(user);
        Session.user = user;
    }

    @Override
    public User login(String email, String password) {
        if (email == null || password == null) {
            throw new LoginException("Wrong data for login!");
        }
        User user = userRepository.findAll().stream()
                .filter(e -> e.getEmail().equals(email) && e.getPassword().equals(password))
                .findFirst().orElse(null);
        if (user == null) throw new LoginException("User with such email does not exist!");
        if (!user.isActive()) throw new LoginException("User is inactive!");
        Session.user = user;
        return user;
    }

    @Override
    public void updateData(User user) {
        userRepository.save(user);
    }

    @Override
    public void deactivateUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(User::isActive).collect(Collectors.toList());
    }

    @Override
    public List<UserType> getAllUserTypes() {
        return userTypeRepository.findAll();
    }

    @Override
    public List<User> getUsersByTitle(String title) {
        return userRepository.findAll()
                .stream()
                .filter(e -> e.getUserType().getTitle().equalsIgnoreCase(title) && e.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public boolean isMainAdmin(User user) {
        return user.getUserType().getTitle().equalsIgnoreCase("Main Admin");
    }

    @Override
    public void upgradeToAdmin(Integer userId) {
        setUserType("Admin", userId);
    }

    @Override
    public void downgradeToUser(Integer userId) {
        setUserType("User", userId);
    }

    @Override
    public boolean isAdmin(User user) {
        return user.getUserType().getTitle().contains("Admin");
    }

    private void setUserType(String title, Integer userId) {
        UserType userType = userTypeRepository.findAll().stream()
                .filter(i -> i.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        user.setUserType(userType);
        userRepository.save(user);
    }


}
