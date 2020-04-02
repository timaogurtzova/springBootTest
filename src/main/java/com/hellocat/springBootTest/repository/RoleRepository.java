package com.hellocat.springBootTest.repository;

import com.hellocat.springBootTest.domen.Role;
import com.hellocat.springBootTest.domen.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> getRoleByRoleType(String roleType);

}
