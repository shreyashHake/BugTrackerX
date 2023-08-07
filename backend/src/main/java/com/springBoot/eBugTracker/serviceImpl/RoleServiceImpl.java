package com.springBoot.eBugTracker.serviceImpl;

import com.springBoot.eBugTracker.entity.Role;
import com.springBoot.eBugTracker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private com.springBoot.eBugTracker.repository.IRoleRepository IRoleRepository;

    public Role createNewRole(Role role) {
        return IRoleRepository.save(role);
    }
}
