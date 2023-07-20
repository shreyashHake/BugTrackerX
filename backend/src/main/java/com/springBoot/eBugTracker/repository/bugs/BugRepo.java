package com.springBoot.eBugTracker.repository.bugs;

import com.springBoot.eBugTracker.entity.bugs.Bug;
import com.springBoot.eBugTracker.entity.customer.CustomerProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BugRepo extends JpaRepository<Bug, Integer> {
    List<Bug> findByCustomerProject(CustomerProject customerProject);
}
