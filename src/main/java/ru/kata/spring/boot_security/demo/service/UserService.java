package ru.kata.spring.boot_security.demo.service;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;
import java.util.Optional;


public interface UserService {

    User findByUserEmail(String email);

    Optional<User> getUser(Long id);

    List<User> getAllUsers();

    void addUser(User user);

    void removeUser(Long id);

    void updateUser(Long id, User user);
}