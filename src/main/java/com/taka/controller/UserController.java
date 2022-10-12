package com.taka.controller;

import com.taka.domain.Municipal;
import com.taka.domain.Users;
import com.taka.repo.MunicipalRepo;
import com.taka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MunicipalRepo muniRepo;

    @PostMapping("/new")
    public Users newUser(@RequestBody Users user){
        return userService.saveNewUser(user);
    }

//    get all users
    @GetMapping("/all")
    public List<Users> getAllUsers(){
        return userService.getAll();
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

    @GetMapping("/count")
    public long totalUsers(){
        return userService.countUsers();
    }
}
