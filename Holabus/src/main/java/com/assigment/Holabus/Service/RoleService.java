package com.assigment.Holabus.Service;

import com.assigment.Holabus.Model.Role;
import com.assigment.Holabus.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository _roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        _roleRepository = roleRepository;
    }

    public Role getUserRole() {
        Optional<Role> role = _roleRepository.getUserRole();
        return role.orElse(null);
    }
}
