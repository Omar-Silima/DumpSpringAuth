package com.taka.controller;

import com.taka.domain.Users;
import com.taka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public Users newUser(@RequestBody Users user){
        return userService.saveNewUser(user);
    }

    @PostConstruct
    public void userRole(){
        userService.initUserRole();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin(){
        return "Only admin can access this content";
    }

    @GetMapping("/municipal")
    @PreAuthorize("hasRole('MUNICIPAL')")
    public String forMunicipal(){
        return "Only municipal can access this content";
    }
}
