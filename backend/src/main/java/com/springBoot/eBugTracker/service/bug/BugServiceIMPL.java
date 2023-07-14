package com.springBoot.eBugTracker.service.bug;

import com.springBoot.eBugTracker.dtos.bugs.BugDetailsDTO;
import com.springBoot.eBugTracker.dtos.bugs.CommentDTO;
import com.springBoot.eBugTracker.entity.bugs.Bug;
import com.springBoot.eBugTracker.entity.bugs.BugProcess;
import com.springBoot.eBugTracker.entity.bugs.Comment;
import com.springBoot.eBugTracker.entity.staff.StaffProfile;
import com.springBoot.eBugTracker.repository.bugs.BugProcessRepo;
import com.springBoot.eBugTracker.repository.bugs.BugRepo;
import com.springBoot.eBugTracker.repository.bugs.CommentRepo;
import com.springBoot.eBugTracker.repository.staff.StaffProfileRepo;
import com.springBoot.eBugTracker.util.DtoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BugServiceIMPL implements BugService{
    @Autowired
    private BugRepo bugRepo;
    @Autowired
    private BugProcessRepo bugProcessRepo;
    @Autowired
    private StaffProfileRepo staffProfileRepo;
    @Autowired
    private CommentRepo commentRepo;
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
        bugProcess.get().setGlobalStatus("Staff "+staffProfile.get().getStaffName()+" Assigned");
        bugProcessRepo.save(bugProcess.get());
        return "Assign Staff To Bug Successfully";
    }

    @Override
    public CommentDTO addComment(Comment comment) {
        Optional<BugProcess> bugProcess = bugProcessRepo.findById(comment.getBugProcess().getBugProcessId());
        comment.setCommentDateTime(LocalDateTime.now());
        comment.setBugProcess(bugProcess.get());
       return dtoHelper.getCommentDto(commentRepo.save(comment));
    }

    @Override
    public BugDetailsDTO getBugDetails(int bugId) {
        Optional<Bug> bug = bugRepo.findById(bugId);
        BugProcess bugProcess = bugProcessRepo.findByBug(bug.get());
        return dtoHelper.getBugDetailsDto(bug.get(),bugProcess);
    }
}
