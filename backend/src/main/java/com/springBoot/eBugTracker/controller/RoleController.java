package com.springBoot.eBugTracker.controller;

import com.springBoot.eBugTracker.entity.Role;
import com.springBoot.eBugTracker.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }
}
