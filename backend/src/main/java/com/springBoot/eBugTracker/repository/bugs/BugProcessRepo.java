package com.springBoot.eBugTracker.repository.bugs;

import com.springBoot.eBugTracker.entity.bugs.Bug;
import com.springBoot.eBugTracker.entity.bugs.BugProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugProcessRepo extends JpaRepository<BugProcess,Integer> {
    BugProcess findByBug(Bug bug);
}
