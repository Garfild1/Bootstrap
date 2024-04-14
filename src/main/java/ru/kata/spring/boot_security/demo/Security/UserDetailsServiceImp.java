package ru.kata.spring.boot_security.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Repository.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw  new UsernameNotFoundException("Error");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), user.getAuthorities());
    }
}
