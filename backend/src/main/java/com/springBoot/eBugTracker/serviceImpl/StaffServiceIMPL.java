package com.springBoot.eBugTracker.serviceImpl;

import com.springBoot.eBugTracker.dtos.bugs.BugProcessDTO;
import com.springBoot.eBugTracker.dtos.staff.StaffProfileDTO;
import com.springBoot.eBugTracker.entity.User;
import com.springBoot.eBugTracker.entity.bugs.BugProcess;
import com.springBoot.eBugTracker.entity.staff.StaffProfile;
import com.springBoot.eBugTracker.repository.IUserRepository;
import com.springBoot.eBugTracker.repository.bugs.BugProcessRepo;
import com.springBoot.eBugTracker.repository.staff.StaffProfileRepo;
import com.springBoot.eBugTracker.service.StaffService;
import com.springBoot.eBugTracker.util.DtoHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
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
        log.info("Inside:- /StaffProfileDTO/createStaffProfile");
        try {
            staffProfile.setCreatedDate(LocalDate.now());
            StaffProfile staffProfile1 = staffProfileRepo.save(staffProfile);
            return dtoHelper.getStaffProfileDto(staffProfile1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new StaffProfileDTO();
    }

    @Override
    public List<StaffProfile> getAllStaff() {
        log.info("Inside:- /StaffProfileDTO/getAllStaff");
        try {
            return staffProfileRepo.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public StaffProfileDTO getStaffProfile(String username) {
        log.info("Inside:- /StaffProfileDTO/getStaffProfile");
        try {
            Optional<User> user = iUserRepository.findById(username);
            if (user.isPresent())
                return dtoHelper.getStaffProfileDto(staffProfileRepo.findByUser(user.get()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new StaffProfileDTO();
    }

    @Override
    public List<BugProcessDTO> getBugsByStaff(int staffId) {
        log.info("Inside:- /StaffProfileDTO/getBugsByStaff");
        try {
            Optional<StaffProfile> staffProfile = staffProfileRepo.findById(staffId);
            List<BugProcessDTO> bugProcessDTOS = new ArrayList<>();

            if (staffProfile.isPresent()) {
                List<BugProcess> bugProcesses = bugProcessRepo.findByStaffProfile(staffProfile.get());
                for (BugProcess bugProcess : bugProcesses) {
                    BugProcessDTO bugProcessDTO = dtoHelper.getBugProcessDto(bugProcess);
                    bugProcessDTOS.add(bugProcessDTO);
                }
            } else {
                System.out.println("Staff Profile not found.");
            }
            return bugProcessDTOS;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public StaffProfileDTO getStaffProfileById(int staffId) {
        log.info("Inside:- /StaffProfileDTO/getStaffProfileById");
        try {
            Optional<StaffProfile> optional = staffProfileRepo.findById(staffId);
            if (optional.isPresent())
                return dtoHelper.getStaffProfileDto(optional.get());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new StaffProfileDTO();
    }

}
