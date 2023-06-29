package com.springBoot.eBugTracker.service;

import com.springBoot.eBugTracker.entity.Project;
import com.springBoot.eBugTracker.repository.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements IProjectService{
    @Autowired
    private IProjectRepository projectRepository;
    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
