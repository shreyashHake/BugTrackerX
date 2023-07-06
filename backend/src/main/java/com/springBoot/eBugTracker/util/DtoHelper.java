package com.springBoot.eBugTracker.util;

import com.springBoot.eBugTracker.dtos.bugs.BugDTO;
import com.springBoot.eBugTracker.dtos.customer.CustomerProfileDTO;
import com.springBoot.eBugTracker.dtos.customer.CustomerProjectDTO;
import com.springBoot.eBugTracker.dtos.customer.UserDTO;
import com.springBoot.eBugTracker.dtos.staff.StaffProfileDTO;
import com.springBoot.eBugTracker.entity.User;
import com.springBoot.eBugTracker.entity.bugs.Bug;
import com.springBoot.eBugTracker.entity.customer.CustomerProfile;
import com.springBoot.eBugTracker.entity.customer.CustomerProject;
import com.springBoot.eBugTracker.entity.staff.StaffProfile;
import org.springframework.stereotype.Service;

@Service
public class DtoHelper {

    public UserDTO getUserDto(User user){
        return new UserDTO(
                user.getUserName()
        );
    }
    public CustomerProfileDTO getCustomerProfileDto(CustomerProfile customerProfile){
        return new CustomerProfileDTO(
                customerProfile.getCustomerProfileId(),
                customerProfile.getCustomerName(),
                customerProfile.getCustomerCompany(),
                customerProfile.getCustomerPhone(),
                customerProfile.getIsActive(),
                customerProfile.getCreatedDate(),
                getUserDto(customerProfile.getUser())
        );
    }

    public CustomerProjectDTO getCustomerProjectDto(CustomerProject customerProject){
        return new CustomerProjectDTO(
                customerProject.getProjectId(),
                customerProject.getProjectName(),
                customerProject.getProjectDomain(),
                customerProject.getProjectDesc(),
                customerProject.getProjectPriority(),
                customerProject.getCreatedDate(),
                customerProject.getEndDate(),
                getCustomerProfileDto(customerProject.getCustomerProfile())
        );
    }

    public BugDTO getBugDto(Bug bug){
        return new BugDTO(
                bug.getBugId(),
                bug.getBugTitle(),
                bug.getBugDesc(),
                bug.getBugStatus(),
                bug.getBugPriority(),
                bug.getCreatedDate(),
                bug.getEndDate(),
                getCustomerProjectDto(bug.getCustomerProject())
        );
    }

    public StaffProfileDTO getStaffProfileDto(StaffProfile staffProfile){
        return new StaffProfileDTO(
                staffProfile.getStaffProfileId(),
                staffProfile.getStaffName(),
                staffProfile.getStaffPhone(),
                staffProfile.getStaffEmail(),
                staffProfile.getIsActive(),
                staffProfile.getCreatedDate(),
                getUserDto(staffProfile.getUser())
        );
    }
}
