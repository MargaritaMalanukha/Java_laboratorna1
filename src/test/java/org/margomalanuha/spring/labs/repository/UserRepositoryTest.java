package org.margomalanuha.spring.labs.repository;

import org.junit.Assert;
import org.junit.Test;
import org.margomalanuha.spring.labs.config.Config;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class UserRepositoryTest {

    public AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    public UserRepository userRepository = context.getBean("userRepository", UserRepository.class);

    @Test
    public void create_WhenDataIsCorrect_ShouldCreateNewRecord() {
        String name = "Natalia";
        String surname = "Ivanova";
        String email = "NI@gmail.com";
        String password = "natasha";
        userRepository.create(new User(name, surname, email, password));
        List<User> list = userRepository.getAll();
        Assert.assertEquals(email, list.get(list.size()-1).getEmail());
    }

    @Test
    public void update_WhenDataIsCorrect_ShouldUpdateRecord() {
        int id = userRepository.getAll().get(userRepository.getAll().size()-1).getId();
        userRepository.update(new User(id, "Natasha", "Ivanova", " ", " ", 1, true));
        List<User> list = userRepository.getAll();
        Assert.assertEquals("Natasha", list.get(list.size()-1).getName());
    }

    @Test
    public void delete_WhenDataIsCorrect_ShouldDeleteRecord() {
        int prevSize = userRepository.getAll().size();
        userRepository.delete(prevSize-1);
        int size = userRepository.getAll().size();
        Assert.assertEquals(prevSize, size);
    }

}
