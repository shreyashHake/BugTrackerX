package com.springBoot.eBugTracker.service;

import com.springBoot.eBugTracker.repository.IRoleRepository;
import com.springBoot.eBugTracker.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface RoleService {
    public Role createNewRole(Role role);
}
