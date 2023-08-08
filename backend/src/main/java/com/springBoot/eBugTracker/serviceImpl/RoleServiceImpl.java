package com.springBoot.eBugTracker.serviceImpl;

import com.springBoot.eBugTracker.entity.Role;
import com.springBoot.eBugTracker.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private com.springBoot.eBugTracker.repository.IRoleRepository IRoleRepository;

    public Role createNewRole(Role role) {
        log.info("Inside:- /RoleServiceImpl/createNewRole");
        try {
            return IRoleRepository.save(role);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new Role();
    }
}
