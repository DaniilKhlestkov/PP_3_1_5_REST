package ru.kata.spring.boot_security.demo.Dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
;import java.util.Set;

@Repository
public interface RoleDao {
    Set<Role> getRoles();

    Role findById(Long id);
    Role findByName(String name);

}
