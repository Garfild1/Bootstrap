package ru.kata.spring.boot_security.demo.Security;

import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.demo.Model.Role;

public class RoleGrantedAuthority implements GrantedAuthority {

    private final Role role;

    public RoleGrantedAuthority(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.getName();
    }
}
