package com.springBoot.eBugTracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(name = "admin")
@RestController
public class AdminController {

    @GetMapping({"/getAdmin"})
    String getAdmin(){
        return "Admin is here";
    }
}
