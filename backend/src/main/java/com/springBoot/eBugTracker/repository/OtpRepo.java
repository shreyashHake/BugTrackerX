package com.springBoot.eBugTracker.repository;

import com.springBoot.eBugTracker.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepo extends JpaRepository<Otp, String> {
}
