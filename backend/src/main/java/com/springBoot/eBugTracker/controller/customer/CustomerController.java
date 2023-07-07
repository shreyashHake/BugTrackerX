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

import java.util.List;

@RestController
@RequestMapping("/customer/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping({"/createCustomerProfile"})
    public CustomerProfileDTO createCustomerProfile(@RequestBody CustomerProfile customerProfile){
        return customerService.createCustomerProfile(customerProfile);
    }
    @PostMapping({"/createCustomerProject"})
//    @PreAuthorize("hasRole('Customer')")
    public CustomerProjectDTO createCustomerProject(@RequestBody CustomerProject customerProject){
        return customerService.createCustomerProject(customerProject);
    }
    @PostMapping({"/addBug"})
    public BugDTO addBug(@RequestBody Bug bug){
        return customerService.addBug(bug);
    }

//    @GetMapping({"/test"})
//    public String test(){
//        Optional<CustomerProfile> customerProjectOptional = customerProfileRepo.findById(1);
//        System.out.println("Customer Project 1 : " +dtoHelper.getCustomerProfileDto(customerProjectOptional.orElse(null)));
//        return "worked";
//    }

    @GetMapping({"/getCustomerProfile/{user_id}"})
    public CustomerProfileDTO getCustomerProfile(@PathVariable String user_id){
        return customerService.getCustomerProfile(user_id);
    }

    @GetMapping({"/haveCustomerProfile/{username}"})
    public boolean haveCustomerProfile(@PathVariable String username){
        return customerService.haveCustomerProfile(username);
    }
    @GetMapping({"/getCustomerProjects/{profile_id}"})
    public List<CustomerProjectDTO> getCustomerProjects(@PathVariable int profile_id){
        return customerService.getCustomerProjects(profile_id);
    }

    @GetMapping({"/getBugs/{project_id}"})
    public List<BugDTO> getBugs(@PathVariable int project_id){
        return customerService.getBugs(project_id);
    }

}
