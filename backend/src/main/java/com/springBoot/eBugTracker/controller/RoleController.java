package com.springBoot.eBugTracker.controller;

import com.springBoot.eBugTracker.entity.Role;
import com.springBoot.eBugTracker.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        log.info("Inside:- /RoleController/createNewRole");
        try {
            return roleService.createNewRole(role);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new Role();
    }
}
