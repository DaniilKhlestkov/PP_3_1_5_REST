package ru.kata.spring.boot_security.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUserEmail(String email) {
        return userDao.findByUserEmail(email);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        user.setId(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Override
    public void removeUser(Long id) {
        userDao.removeUser(id);
    }

    @Override
    public void updateUser(Long id, User user) {
        var user1 = userDao.getUser(id);
        if (user1.isPresent()) {
            User existsUser = user1.get();
            existsUser.setAge(user.getAge());
            existsUser.setEmail(user.getEmail());
            existsUser.setPassword(passwordEncoder.encode(user.getPassword()));
            existsUser.setLastname(user.getLastname());
            existsUser.setRoles(user.getRoles());
            existsUser.setUsername(user.getUsername());
            userDao.addUser(existsUser);
        } else {
            throw new EntityNotFoundException("User with Id " + id + " not found");
        }
    }

    @PostConstruct
    public void init() {
        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleDao.addRole(userRole);

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleDao.addRole(adminRole);

        List<Role> userRoles = Arrays.asList(userRole);
        List<Role> adminRoles = Arrays.asList(adminRole);

        User user = new User();
        user.setPassword(passwordEncoder.encode("100"));
        user.setAge((byte) 20);
        user.setRoles(userRoles);
        user.setEmail("daniil@mail.ru");
        user.setUsername("daniil");
        user.setLastname("xlestkov");

        User admin = new User();
        admin.setPassword(passwordEncoder.encode("100"));
        admin.setAge((byte) 21);
        admin.setRoles(adminRoles);
        admin.setEmail("egor@mail.ru");
        admin.setUsername("egor");
        admin.setLastname("rozin");

        userDao.addUser(admin);
        userDao.addUser(user);

    }
}


