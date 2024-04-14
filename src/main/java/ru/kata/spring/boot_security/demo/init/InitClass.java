package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.Model.Role;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Repository.UserRepository;
import ru.kata.spring.boot_security.demo.Service.RoleServiceImp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitClass implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleServiceImp roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public InitClass(RoleServiceImp roleService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public  void onApplicationEvent(ContextRefreshedEvent event) {

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleService.save(userRole);

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleService.save(adminRole);

        Set<Role> userRoles = new HashSet<>(Arrays.asList(userRole));
        Set<Role> adminRoles = new HashSet<>(Arrays.asList(adminRole));

        User admin = new User ();
        admin.setUsername("admin");
        admin.setName("SomeNameAdmin");
        admin.setLastName("SomeLastNameAdmin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("A@mail.ru");
        admin.setRoles(adminRoles);

        User user = new User();
        user.setUsername("user");
        user.setName("SomeNameUser");
        user.setLastName("SomeLastName");
        user.setPassword(passwordEncoder.encode("user"));
        user.setEmail("U@gmail.com");
        user.setRoles(userRoles);

        userRepository.save(admin);
        userRepository.save(user);
    }
}

