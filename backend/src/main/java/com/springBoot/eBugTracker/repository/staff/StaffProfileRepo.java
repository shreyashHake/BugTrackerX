package com.springBoot.eBugTracker.repository.staff;

import com.springBoot.eBugTracker.entity.staff.StaffProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffProfileRepo extends JpaRepository<StaffProfile,Integer> {
}
