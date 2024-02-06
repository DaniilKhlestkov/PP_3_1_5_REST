package ru.kata.spring.boot_security.demo.Dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
;import java.util.List;


@Repository
public interface RoleDao {
    List<Role> getRoles();

    Role findById(Long id);
    void addRole(Role role);
}
