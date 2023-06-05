package com.springBoot.eBugTracker.repository;

import com.springBoot.eBugTracker.entity.Role;
import com.springBoot.eBugTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
//    List<User> findUserByUserRole(Role role);
}
