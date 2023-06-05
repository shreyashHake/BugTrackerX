package com.springBoot.eBugTracker.service;

import com.springBoot.eBugTracker.entity.Role;
import com.springBoot.eBugTracker.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    public IRoleRepository roleRepository;

    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }
}
