package com.springBoot.eBugTracker.controller.bug;

import com.springBoot.eBugTracker.service.bug.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bug/")
public class BugController {
    @Autowired
    private BugService bugService;
    @GetMapping({"/getTest"})
    public String getTest(){
        return "getTest worked";
    }
    @PutMapping({"/assignStaff/{bugProcessId}"})
    public String assignStaff(@PathVariable int bugProcessId,@RequestBody int staffProfileId ){
        return bugService.assignStaff(bugProcessId,staffProfileId);
    }
}
