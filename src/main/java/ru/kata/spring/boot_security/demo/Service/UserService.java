package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.Model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User getUserById(Long id);
    User findUserByUsername(String username);

    void save(User user);

    void deleteById(Long id);

    void update(User user);
}
