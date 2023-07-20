package com.springBoot.eBugTracker.repository.customer;

import com.springBoot.eBugTracker.entity.customer.CustomerProfile;
import com.springBoot.eBugTracker.entity.customer.CustomerProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerProjectRepo extends JpaRepository<CustomerProject, Integer> {
    List<CustomerProject> findByCustomerProfile(CustomerProfile customerProfile);
}
