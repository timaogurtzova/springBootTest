package com.hellocat.springBootTest.service;

import com.hellocat.springBootTest.domen.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAllRoles();

    Role findRoleWithName(String roleTypeName);

}
