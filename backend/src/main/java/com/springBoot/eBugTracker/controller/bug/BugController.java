package com.springBoot.eBugTracker.controller.bug;

import com.springBoot.eBugTracker.dtos.bugs.BugDetailsDTO;
import com.springBoot.eBugTracker.dtos.bugs.CommentDTO;
import com.springBoot.eBugTracker.entity.bugs.Comment;
import com.springBoot.eBugTracker.service.bug.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bug/")
public class BugController {
    @Autowired
    private BugService bugService;

    @PreAuthorize("hasAnyRole('Staff', 'Admin')")
    @PutMapping({"/assignStaff/{bugProcessId}"})
    public String assignStaff(@PathVariable int bugProcessId, @RequestBody int staffProfileId) {
        return bugService.assignStaff(bugProcessId, staffProfileId);
    }

    @GetMapping({"/getBugDetails/{bugId}"})
    public BugDetailsDTO getBugDetails(@PathVariable int bugId) {
        return bugService.getBugDetails(bugId);
    }

    @PreAuthorize("hasAnyRole('Customer', 'Staff')")
    @PostMapping({"/addComment"})
    public CommentDTO addComment(@RequestBody Comment comment) {
        return bugService.addComment(comment);
    }

    @PutMapping({"/changeBugStatus/{bugId}"})
    public String changeBugStatus(@PathVariable int bugId, @RequestBody String bugStatus) {
        return bugService.changeBugStatus(bugId, bugStatus);
    }

    @PutMapping({"/changeGlobalBugStatus/{bugProcessId}"})
    public String changeGlobalBugStatus(@PathVariable int bugProcessId, @RequestBody String globalBugStatus) {
        return bugService.changeGlobalBugStatus(bugProcessId, globalBugStatus);
    }
}
