package com.hellocat.springBootTest.service;

import com.hellocat.springBootTest.domen.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getAllRoles();

    Optional<Role> getRoleWithName(String roleTypeName);

}
