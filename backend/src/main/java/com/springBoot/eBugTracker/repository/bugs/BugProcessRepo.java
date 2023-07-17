package com.springBoot.eBugTracker.repository.bugs;

import com.springBoot.eBugTracker.entity.bugs.Bug;
import com.springBoot.eBugTracker.entity.bugs.BugProcess;
import com.springBoot.eBugTracker.entity.staff.StaffProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BugProcessRepo extends JpaRepository<BugProcess,Integer> {
    BugProcess findByBug(Bug bug);
    List<BugProcess> findByStaffProfile(StaffProfile staffProfile);

}
