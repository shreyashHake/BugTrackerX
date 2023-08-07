package com.springBoot.eBugTracker.controller.customer;

import com.springBoot.eBugTracker.dtos.bugs.BugDTO;
import com.springBoot.eBugTracker.dtos.customer.CustomerProfileDTO;
import com.springBoot.eBugTracker.dtos.customer.CustomerProjectDTO;
import com.springBoot.eBugTracker.entity.bugs.Bug;
import com.springBoot.eBugTracker.entity.customer.CustomerProfile;
import com.springBoot.eBugTracker.entity.customer.CustomerProject;
import com.springBoot.eBugTracker.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/customer/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Customer mappings :

    @PostMapping({"/createCustomerProfile"})
    public CustomerProfileDTO createCustomerProfile(@RequestBody CustomerProfile customerProfile) {
        return customerService.createCustomerProfile(customerProfile);
    }

    @PreAuthorize("hasRole('Customer')")
    @PostMapping({"/createCustomerProject"})
    public CustomerProjectDTO createCustomerProject(@RequestBody CustomerProject customerProject) {
        return customerService.createCustomerProject(customerProject);
    }

    @GetMapping({"/getCustomerProfile/{user_id}"})
    public CustomerProfileDTO getCustomerProfile(@PathVariable String user_id) {
        return customerService.getCustomerProfile(user_id);
    }

    @GetMapping({"/getCustomerProfileById/{customer_id}"})
    public CustomerProfileDTO getCustomerProfileById(@PathVariable int customer_id) {
        return customerService.getCustomerProfileById(customer_id);
    }

    @GetMapping({"/haveCustomerProfile/{username}"})
    public boolean haveCustomerProfile(@PathVariable String username) {
        return customerService.haveCustomerProfile(username);
    }

    @PreAuthorize("hasAnyRole('Customer', 'Admin')")
    @GetMapping({"/getCustomerProjects/{profile_id}"})
    public List<CustomerProjectDTO> getCustomerProjects(@PathVariable int profile_id) {
        return customerService.getCustomerProjects(profile_id);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getAllCustomer"})
    public List<CustomerProfileDTO> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    // Bugs
    @GetMapping({"/getBugs/{project_id}"})
    public List<BugDTO> getBugs(@PathVariable int project_id) {
        return customerService.getBugs(project_id);
    }

    @PreAuthorize("hasRole('Customer')")
    @PostMapping({"/addBug"})
    public BugDTO addBug(@RequestBody Bug bug) {
        return customerService.addBug(bug);
    }

    @GetMapping({"/verifyEmail/{email}/{token}"})
    public RedirectView verifyEmail(@PathVariable String email, @PathVariable int token) {
        return customerService.verifyEmail(email, token);
    }
}
