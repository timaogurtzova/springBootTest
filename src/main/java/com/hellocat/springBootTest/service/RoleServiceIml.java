package com.hellocat.springBootTest.service;

import com.hellocat.springBootTest.domen.Role;
import com.hellocat.springBootTest.domen.RoleType;
import com.hellocat.springBootTest.domen.User;
import com.hellocat.springBootTest.repository.RoleRepository;
import com.hellocat.springBootTest.util.PostProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class RoleServiceIml implements RoleService {

    @Autowired
    private UserService userService;

    private RoleRepository roleRepository;

    @PostProxy
    public void dbRequest(){
        Role adminRole = new Role(RoleType.ROLE_ADMIN);
        Role userRole = new Role(RoleType.ROLE_USER);
        roleRepository.save(adminRole);
        roleRepository.save(userRole);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        roles.add(adminRole);
        User user = new User("user", 10, "1", "Msk", roles);
        userService.saveUser(user);
    }

    @Autowired
    public RoleServiceIml(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleWithName(String roleTypeName) {
        return roleRepository.getRoleByRoleType(roleTypeName);
    }
}
