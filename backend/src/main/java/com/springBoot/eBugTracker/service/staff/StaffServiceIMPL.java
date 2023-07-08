package com.springBoot.eBugTracker.service.staff;

import com.springBoot.eBugTracker.dtos.customer.CustomerProjectDTO;
import com.springBoot.eBugTracker.dtos.staff.StaffProfileDTO;
import com.springBoot.eBugTracker.entity.customer.CustomerProfile;
import com.springBoot.eBugTracker.entity.customer.CustomerProject;
import com.springBoot.eBugTracker.entity.staff.StaffProfile;
import com.springBoot.eBugTracker.repository.staff.StaffProfileRepo;
import com.springBoot.eBugTracker.util.DtoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StaffServiceIMPL implements StaffService {
    @Autowired
    private StaffProfileRepo staffProfileRepo;
    @Autowired
    private DtoHelper dtoHelper;

    @Override
    public StaffProfileDTO createStaffProfile(StaffProfile staffProfile) {
        System.out.println("Staff profile : " + staffProfile);
        staffProfile.setCreatedDate(LocalDate.now());

        StaffProfile staffProfile1 = staffProfileRepo.save(staffProfile);
        return dtoHelper.getStaffProfileDto(staffProfile1);
    }

    @Override
    public List<StaffProfile> getAllStaff() {
        return staffProfileRepo.findAll();
    }
}
