package ru.kata.spring.boot_security.demo.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Model.Role;
import ru.kata.spring.boot_security.demo.Repository.RoleRepository;

import java.util.List;

@Service
@Transactional (readOnly = true)
@Slf4j
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll () {
        return roleRepository.findAll();
    }
    @Override
    @Transactional
    public void save (Role role) {
        roleRepository.save(role);
    }
    @Override
    @Transactional
    public Role getById (Long id) {
        return roleRepository.getById(id);
    }
}
