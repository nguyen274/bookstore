package com.project.library.controller;

import com.project.library.model.Role;
import com.project.library.model.Staff;
import com.project.library.model.User;
import com.project.library.repository.RoleRepository;
import com.project.library.service.StaffService;
import com.project.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/staff")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StaffService staffService;

    @GetMapping("/register/{id}")
    public String register(@PathVariable("id") Long id, Model model){

        model.addAttribute("user", new User());
        Optional<Staff> staffEdit = staffService.findStaffById(id);
        staffEdit.ifPresent(staff -> model.addAttribute("staffs", staff));
        return "staff/register";
    }

    @GetMapping("/authentication/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        Optional<User> userList = userService.findUserById(id);
        List<Role> listRoles = roleRepository.findAll();
        model.addAttribute("user", userList);
        model.addAttribute("listRoles", listRoles);
        return "staff/edit";
    }
    @PostMapping("/update-user")
    public String saveUser(User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @PostMapping("/save-user")
    public String userRegistration( final @Valid User userData, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("registrationForm", userData);
            return "staff/register";
        }
        try {
            Role roleUser = roleRepository.findByName("User");
            userData.addRole(roleUser);
            userService.register(userData);
        }catch (ExceptionInInitializerError e){
            bindingResult.rejectValue("email", "userData.email","An account already exists for this email.");
            model.addAttribute("email", userData);
            return "staff/register";
        }
        return "redirect:/staff";
    }
}

