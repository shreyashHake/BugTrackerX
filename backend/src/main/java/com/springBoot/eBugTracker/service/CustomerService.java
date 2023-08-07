package com.springBoot.eBugTracker.service;

import com.springBoot.eBugTracker.dtos.bugs.BugDTO;
import com.springBoot.eBugTracker.dtos.customer.CustomerProfileDTO;
import com.springBoot.eBugTracker.dtos.customer.CustomerProjectDTO;
import com.springBoot.eBugTracker.entity.bugs.Bug;
import com.springBoot.eBugTracker.entity.customer.CustomerProfile;
import com.springBoot.eBugTracker.entity.customer.CustomerProject;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

public interface CustomerService {
    CustomerProfileDTO createCustomerProfile(CustomerProfile customerProfile);

    CustomerProjectDTO createCustomerProject(CustomerProject customerProject);

    BugDTO addBug(Bug bug);

    CustomerProfileDTO getCustomerProfile(String userId);

    List<CustomerProjectDTO> getCustomerProjects(int profileId);

    List<BugDTO> getBugs(int projectId);

    boolean haveCustomerProfile(String username);

    List<CustomerProfileDTO> getAllCustomer();

    CustomerProfileDTO getCustomerProfileById(int customerId);

    RedirectView verifyEmail(String email, int token);
}
