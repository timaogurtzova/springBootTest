package com.hellocat.springBootTest.repository;

import com.hellocat.springBootTest.domen.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByRoleType(String roleType);

}
