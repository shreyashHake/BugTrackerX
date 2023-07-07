package com.springBoot.eBugTracker.controller.staff;

import com.springBoot.eBugTracker.dtos.staff.StaffProfileDTO;
import com.springBoot.eBugTracker.entity.staff.StaffProfile;
import com.springBoot.eBugTracker.service.staff.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff/")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @PostMapping({"/createStaffProfile"})
    public StaffProfileDTO createStaffProfile(@RequestBody StaffProfile staffProfile){
        return staffService.createStaffProfile(staffProfile);
    }
}
