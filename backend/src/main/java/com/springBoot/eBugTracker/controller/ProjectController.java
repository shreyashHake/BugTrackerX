package com.springBoot.eBugTracker.controller;

import com.springBoot.eBugTracker.entity.Project;
import com.springBoot.eBugTracker.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private IProjectService projectService;

    @PostMapping("/addProject")
    @PreAuthorize("hasRole('Customer')")
    public Project addProject(@RequestBody Project project) {
        return projectService.addProject(project);
    }

    @GetMapping("/getProjects")
    @PreAuthorize("hasAnyRole('Customer', 'Admin', 'Staff')")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }
}
