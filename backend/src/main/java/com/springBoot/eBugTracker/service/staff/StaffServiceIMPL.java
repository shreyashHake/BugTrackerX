package com.springBoot.eBugTracker.service.staff;

import com.springBoot.eBugTracker.dtos.bugs.BugProcessDTO;
import com.springBoot.eBugTracker.dtos.customer.CustomerProjectDTO;
import com.springBoot.eBugTracker.dtos.staff.StaffProfileDTO;
import com.springBoot.eBugTracker.entity.User;
import com.springBoot.eBugTracker.entity.bugs.BugProcess;
import com.springBoot.eBugTracker.entity.customer.CustomerProfile;
import com.springBoot.eBugTracker.entity.customer.CustomerProject;
import com.springBoot.eBugTracker.entity.staff.StaffProfile;
import com.springBoot.eBugTracker.repository.IUserRepository;
import com.springBoot.eBugTracker.repository.bugs.BugProcessRepo;
import com.springBoot.eBugTracker.repository.staff.StaffProfileRepo;
import com.springBoot.eBugTracker.util.DtoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceIMPL implements StaffService {
    @Autowired
    private StaffProfileRepo staffProfileRepo;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private BugProcessRepo bugProcessRepo;
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

    @Override
    public StaffProfileDTO getStaffProfile(String username) {
        Optional<User> user = iUserRepository.findById(username);
        return dtoHelper.getStaffProfileDto(staffProfileRepo.findByUser(user.get()));
    }

    @Override
    public List<BugProcessDTO> getBugsByStaff(int staffId) {
        Optional<StaffProfile> staffProfile = staffProfileRepo.findById(staffId);
        List<BugProcessDTO> bugProcessDTOS = new ArrayList<>();
        System.out.println("Staff Profile : "+staffProfile);
        try {
            List<BugProcess> bugProcesses = bugProcessRepo.findByStaffProfile(staffProfile.get());
            System.out.println("Bug Processes : "+bugProcesses);
            for (BugProcess bugProcess : bugProcesses) {
                System.out.println("Bug Process : "+bugProcess);
                BugProcessDTO bugProcessDTO = dtoHelper.getBugProcessDto(bugProcess);
                bugProcessDTOS.add(bugProcessDTO);
            }
            System.out.println("Bug Process DTO : "+bugProcessDTOS);

        }catch (Exception e){
            System.out.println("No Data Found");
            return null;
        }
        return bugProcessDTOS;
    }
}
