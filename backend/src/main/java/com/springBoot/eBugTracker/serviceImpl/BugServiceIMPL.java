package com.springBoot.eBugTracker.serviceImpl;

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
import com.springBoot.eBugTracker.service.BugService;
import com.springBoot.eBugTracker.util.DtoHelper;
import com.springBoot.eBugTracker.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class BugServiceIMPL implements BugService {
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
        log.info("Inside:- /BugServiceIMPL");
        try {
            System.out.println("bid : " + bugProcessId + " staffId : " + staffProfileId);

            Optional<BugProcess> bugProcess = bugProcessRepo.findById(bugProcessId);
            if (bugProcess.isEmpty()) {
                return "Invalid Bug";
            }

            System.out.println("bugProcess : " + bugProcess.get());
            Optional<StaffProfile> staffProfile = staffProfileRepo.findById(staffProfileId);
            if (staffProfile.isEmpty()) {
                return "Invalid Staff";
            }
            System.out.println("staffProfile : " + staffProfile.get());
            bugProcess.get().setStaffProfile(staffProfile.get());
            bugProcess.get().setGlobalStatus("Staff " + staffProfile.get().getStaffName() + " Assigned");
            bugProcessRepo.save(bugProcess.get());
            return "Assign Staff To Bug Successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.generalResponse();
    }

    @Override
    public CommentDTO addComment(Comment comment) {
        log.info("Inside:- /BugServiceIMPL");
        try {
            Optional<BugProcess> bugProcessOptional = bugProcessRepo.findById(comment.getBugProcess().getBugProcessId());
            if (bugProcessOptional.isPresent()) {
                comment.setCommentDateTime(LocalDateTime.now());
                comment.setBugProcess(bugProcessOptional.get());
                return dtoHelper.getCommentDto(commentRepo.save(comment));
            } else {
                return new CommentDTO();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new CommentDTO();
    }

    @Override
    public BugDetailsDTO getBugDetails(int bugId) {
        log.info("Inside:- /BugServiceIMPL");
        try {
            Optional<Bug> bugOptional = bugRepo.findById(bugId);
            if (bugOptional.isPresent()) {
                BugProcess bugProcess = bugProcessRepo.findByBug(bugOptional.get());
                return dtoHelper.getBugDetailsDto(bugOptional.get(), bugProcess);
            } else {
                return new BugDetailsDTO();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new BugDetailsDTO();
    }

    @Override
    public void getTest(int id) {
        log.info("Inside:- /BugServiceIMPL");
        try {
            Optional<BugProcess> bugProcess = bugProcessRepo.findById(id);
            if (bugProcess.isPresent()) {
                System.out.println("bugProcess : " + bugProcess.get());
            } else {
                System.out.println("Bug-Process not found");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String changeBugStatus(int bugId, String bugStatus) {
        log.info("Inside:- /BugServiceIMPL");
        try {
            Optional<Bug> bug = bugRepo.findById(bugId);
            if (bug.isPresent()) {
                bug.get().setBugStatus(bugStatus);
                bugRepo.save(bug.get());
                return "Bug Status Changed Successfully";
            } else {
                return "Failed to change bug status";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.generalResponse();
    }

    @Override
    public String changeGlobalBugStatus(int bugProcessId, String globalBugStatus) {
        log.info("Inside:- /BugServiceIMPL");
        try {
            Optional<BugProcess> bugProcess = bugProcessRepo.findById(bugProcessId);
            if (bugProcess.isPresent()) {
                bugProcess.get().setGlobalStatus(globalBugStatus);
                bugProcessRepo.save(bugProcess.get());
                return "Bug Process Status Changed Successfully";
            }
            return "Failed to change progress";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.generalResponse();
    }

}
