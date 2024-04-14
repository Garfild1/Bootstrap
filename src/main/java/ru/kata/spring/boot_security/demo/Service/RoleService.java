package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.Model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();

    void save(Role role);

    Role getById(Long id);
}
