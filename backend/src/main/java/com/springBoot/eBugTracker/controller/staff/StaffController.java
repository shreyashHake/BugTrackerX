package com.springBoot.eBugTracker.controller.staff;

import com.springBoot.eBugTracker.dtos.bugs.BugProcessDTO;
import com.springBoot.eBugTracker.dtos.staff.StaffProfileDTO;
import com.springBoot.eBugTracker.entity.staff.StaffProfile;
import com.springBoot.eBugTracker.service.staff.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff/")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping({"/createStaffProfile"})
    public StaffProfileDTO createStaffProfile(@RequestBody StaffProfile staffProfile) {
        return staffService.createStaffProfile(staffProfile);
    }

    @GetMapping({"/getStaffProfile/{username}"})
    public StaffProfileDTO getStaffProfile(@PathVariable String username) {
        return staffService.getStaffProfile(username);
    }
    @GetMapping({"/getAllStaff"})
    public List<StaffProfile> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping({"/getBugsByStaff/{staff_id}"})
    public List<BugProcessDTO> getBugsByStaff(@PathVariable int staff_id) {
        return staffService.getBugsByStaff(staff_id);
    }
}
