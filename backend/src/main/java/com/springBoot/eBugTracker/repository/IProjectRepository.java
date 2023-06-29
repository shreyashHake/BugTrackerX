package com.springBoot.eBugTracker.repository;

import com.springBoot.eBugTracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Integer> {
}
