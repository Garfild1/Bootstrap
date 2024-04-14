package ru.kata.spring.boot_security.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Model.User;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.Service.UserServiceImp;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public AdminController(UserServiceImp userService, RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String showUsers (ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("user", userService.findUserByUsername(principal.getName()));
        modelMap.addAttribute("usersOfList", userService.findAll());
        modelMap.addAttribute("roles", roleService.getAll());
        modelMap.addAttribute("newUser", new User());
        return "adminPanel";
    }


    @PostMapping("/add")
    public String createUser (User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user, @RequestParam("roles") Long[] rolesId) {
        userService.update(user);
        return "redirect:/admin";
    }

    @PatchMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);

        return "redirect:/admin";
    }
}
