package com.springBoot.eBugTracker.service.staff;

import com.springBoot.eBugTracker.dtos.staff.StaffProfileDTO;
import com.springBoot.eBugTracker.entity.staff.StaffProfile;

public interface StaffService {
    StaffProfileDTO createStaffProfile(StaffProfile staffProfile);
}
