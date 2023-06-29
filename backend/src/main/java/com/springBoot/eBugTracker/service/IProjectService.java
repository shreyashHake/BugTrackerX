package com.springBoot.eBugTracker.service;

import com.springBoot.eBugTracker.entity.Project;

import java.util.List;

public interface IProjectService {
    Project addProject(Project project);

    List<Project> getAllProjects();
}
