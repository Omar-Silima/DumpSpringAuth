package com.taka.controller;

import com.taka.domain.Role;
import com.taka.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role/")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/new")
    public Role newRole(@RequestBody Role role){
        return roleService.createNewRole(role);
    }
}
