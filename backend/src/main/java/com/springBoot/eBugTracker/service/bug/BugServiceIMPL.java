package com.springBoot.eBugTracker.service.bug;

import com.springBoot.eBugTracker.entity.bugs.BugProcess;
import com.springBoot.eBugTracker.entity.staff.StaffProfile;
import com.springBoot.eBugTracker.repository.bugs.BugProcessRepo;
import com.springBoot.eBugTracker.repository.staff.StaffProfileRepo;
import com.springBoot.eBugTracker.util.DtoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BugServiceIMPL implements BugService{
    @Autowired
    private BugProcessRepo bugProcessRepo;
    @Autowired
    private StaffProfileRepo staffProfileRepo;
    @Autowired
    private DtoHelper dtoHelper;
    @Override
    public String assignStaff(int bugProcessId, int staffProfileId) {
        System.out.println("bpid : "+bugProcessId+" staffId : "+staffProfileId);
        Optional<BugProcess> bugProcess = bugProcessRepo.findById(bugProcessId);
        if(bugProcess.isEmpty()){
            return "Invalid Bug";
        }
        System.out.println("bugProcess : "+bugProcess.get());
        Optional<StaffProfile> staffProfile = staffProfileRepo.findById(staffProfileId);
        if (staffProfile.isEmpty()){
            return "Invalid Staff";
        }
        System.out.println("staffProfile : "+staffProfile.get());
        bugProcess.get().setStaffProfile(staffProfile.get());
        bugProcessRepo.save(bugProcess.get());
        return "Assign Staff To Bug Successfully";
    }
}
