package com.springBoot.eBugTracker.repository.customer;

import com.springBoot.eBugTracker.entity.User;
import com.springBoot.eBugTracker.entity.customer.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProfileRepo extends JpaRepository<CustomerProfile,Integer> {
    CustomerProfile findByUser(User user);
}
