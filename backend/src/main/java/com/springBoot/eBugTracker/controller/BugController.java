package com.springBoot.eBugTracker.controller;

import com.springBoot.eBugTracker.dtos.bugs.BugDetailsDTO;
import com.springBoot.eBugTracker.dtos.bugs.CommentDTO;
import com.springBoot.eBugTracker.entity.bugs.Comment;
import com.springBoot.eBugTracker.service.BugService;
import com.springBoot.eBugTracker.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bug/")
@Slf4j
public class BugController {
    @Autowired
    private BugService bugService;

    @PreAuthorize("hasAnyRole('Staff', 'Admin')")
    @PutMapping({"/assignStaff/{bugProcessId}"})
    public String assignStaff(@PathVariable int bugProcessId, @RequestBody int staffProfileId) {
        log.info("Inside: /BugController/assignStaff");
        try {
            return bugService.assignStaff(bugProcessId, staffProfileId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.generalResponse();
    }

    @GetMapping({"/getBugDetails/{bugId}"})
    public BugDetailsDTO getBugDetails(@PathVariable int bugId) {
        log.info("Inside: /BugController/getBugDetails");
        try {
            return bugService.getBugDetails(bugId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new BugDetailsDTO();
    }

    @PreAuthorize("hasAnyRole('Customer', 'Staff')")
    @PostMapping({"/addComment"})
    public CommentDTO addComment(@RequestBody Comment comment) {
        log.info("Inside: /BugController/addComment");
        try {
            return bugService.addComment(comment);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new CommentDTO();
    }

    @PutMapping({"/changeBugStatus/{bugId}"})
    public String changeBugStatus(@PathVariable int bugId, @RequestBody String bugStatus) {
        log.info("Inside: /BugController/changeBugStatus");
        try {
            return bugService.changeBugStatus(bugId, bugStatus);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.generalResponse();
    }

    @PutMapping({"/changeGlobalBugStatus/{bugProcessId}"})
    public String changeGlobalBugStatus(@PathVariable int bugProcessId, @RequestBody String globalBugStatus) {
        log.info("Inside: /BugController/changeGlobalBugStatus");
        try {
            return bugService.changeGlobalBugStatus(bugProcessId, globalBugStatus);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.generalResponse();
    }
}
