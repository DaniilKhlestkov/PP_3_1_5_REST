package ru.kata.spring.boot_security.demo.Dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.List;

@Repository
public interface UserDao {
    User findByUserEmail(String email);

    User getUser(Long id);

    List<User> getAllUsers();

    void addUser(User user);

    void removeUser(Long id);

    void updateUser(User user);

}
