package com.springBoot.eBugTracker.service.staff;

import com.springBoot.eBugTracker.dtos.staff.StaffProfileDTO;
import com.springBoot.eBugTracker.entity.staff.StaffProfile;
import com.springBoot.eBugTracker.repository.staff.StaffProfileRepo;
import com.springBoot.eBugTracker.util.DtoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StaffServiceIMPL implements StaffService {
    @Autowired
    private StaffProfileRepo staffProfileRepo;
    @Autowired
    private DtoHelper dtoHelper;

    @Override
    public StaffProfileDTO createStaffProfile(StaffProfile staffProfile) {
        staffProfile.setCreatedDate(LocalDate.now());
        return dtoHelper.getStaffProfileDto(staffProfileRepo.save(staffProfile));
    }
}
