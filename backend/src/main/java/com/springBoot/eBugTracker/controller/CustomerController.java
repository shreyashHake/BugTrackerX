package com.springBoot.eBugTracker.controller;

import com.springBoot.eBugTracker.dtos.bugs.BugDTO;
import com.springBoot.eBugTracker.dtos.customer.CustomerProfileDTO;
import com.springBoot.eBugTracker.dtos.customer.CustomerProjectDTO;
import com.springBoot.eBugTracker.entity.bugs.Bug;
import com.springBoot.eBugTracker.entity.customer.CustomerProfile;
import com.springBoot.eBugTracker.entity.customer.CustomerProject;
import com.springBoot.eBugTracker.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Customer mappings :

    @PostMapping({"/createCustomerProfile"})
    public CustomerProfileDTO createCustomerProfile(@RequestBody CustomerProfile customerProfile) {
        log.info("Inside;- /CustomerController/createCustomerProfile");
        try {
            return customerService.createCustomerProfile(customerProfile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new CustomerProfileDTO();
    }

    @PreAuthorize("hasRole('Customer')")
    @PostMapping({"/createCustomerProject"})
    public CustomerProjectDTO createCustomerProject(@RequestBody CustomerProject customerProject) {
        log.info("Inside:- /CustomerController/createCustomerProject");
        try {
            return customerService.createCustomerProject(customerProject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new CustomerProjectDTO();
    }

    @GetMapping({"/getCustomerProfile/{user_id}"})
    public CustomerProfileDTO getCustomerProfile(@PathVariable String user_id) {
        log.info("Inside /CustomerController/getCustomerProfile");
        try {
            return customerService.getCustomerProfile(user_id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new CustomerProfileDTO();
    }

    @GetMapping({"/getCustomerProfileById/{customer_id}"})
    public CustomerProfileDTO getCustomerProfileById(@PathVariable int customer_id) {
        log.info("Inside:- /CustomerController/getCustomerProfileById");
        try {
            return customerService.getCustomerProfileById(customer_id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new CustomerProfileDTO();
    }

    @GetMapping({"/haveCustomerProfile/{username}"})
    public boolean haveCustomerProfile(@PathVariable String username) {
        log.info("Inside:- /CustomerController/haveCustomerProfile");
        try {
            return customerService.haveCustomerProfile(username);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  false;
    }

    @PreAuthorize("hasAnyRole('Customer', 'Admin')")
    @GetMapping({"/getCustomerProjects/{profile_id}"})
    public List<CustomerProjectDTO> getCustomerProjects(@PathVariable int profile_id) {
        log.info("Inside:- /CustomerController/getCustomerProjects");
        try {
            return customerService.getCustomerProjects(profile_id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getAllCustomer"})
    public List<CustomerProfileDTO> getAllCustomer() {
        log.info("Inside:- /CustomerController/getAllCustomer");
        try {
            return customerService.getAllCustomer();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    // Bugs
    @GetMapping({"/getBugs/{project_id}"})
    public List<BugDTO> getBugs(@PathVariable int project_id) {
        log.info("Inside:- /CustomerController/getBugs");
        try {
            return customerService.getBugs(project_id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @PreAuthorize("hasRole('Customer')")
    @PostMapping({"/addBug"})
    public BugDTO addBug(@RequestBody Bug bug) {
        log.info("Inside:- /CustomerController/addBug");
        try {
            return customerService.addBug(bug);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new BugDTO();
    }

    @GetMapping({"/verifyEmail/{email}/{token}"})
    public RedirectView verifyEmail(@PathVariable String email, @PathVariable int token) {
        log.info("Inside:- /CustomerController/verifyEmail");
        try {
            return customerService.verifyEmail(email, token);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new RedirectView();
    }
}
