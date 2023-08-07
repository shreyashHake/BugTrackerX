package com.springBoot.eBugTracker.controller;

import com.springBoot.eBugTracker.dtos.bugs.BugProcessDTO;
import com.springBoot.eBugTracker.dtos.staff.StaffProfileDTO;
import com.springBoot.eBugTracker.entity.staff.StaffProfile;
import com.springBoot.eBugTracker.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/staff/")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping({"/createStaffProfile"})
    public StaffProfileDTO createStaffProfile(@RequestBody StaffProfile staffProfile) {
        log.info("Inside:- /StaffController/createStaffProfile");
        try {
            return staffService.createStaffProfile(staffProfile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new StaffProfileDTO();
    }

    @GetMapping({"/getStaffProfile/{username}"})
    public StaffProfileDTO getStaffProfile(@PathVariable String username) {
        log.info("Inside:- /StaffController/getStaffProfile");
        try {
            return staffService.getStaffProfile(username);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new StaffProfileDTO();
    }

    @GetMapping({"/getStaffProfileById/{staffId}"})
    public StaffProfileDTO getStaffProfileById(@PathVariable int staffId) {
        log.info("Inside:- /StaffController/getStaffProfileById");
        try {
            return staffService.getStaffProfileById(staffId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new StaffProfileDTO();
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getAllStaff"})
    public List<StaffProfile> getAllStaff() {
        log.info("Inside:- /StaffController/getAllStaff");
        try {
            return staffService.getAllStaff();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @GetMapping({"/getBugsByStaff/{staff_id}"})
    public List<BugProcessDTO> getBugsByStaff(@PathVariable int staff_id) {
        log.info("Inside:- /StaffController/getBugsByStaff");
        try {
            return staffService.getBugsByStaff(staff_id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }
}
