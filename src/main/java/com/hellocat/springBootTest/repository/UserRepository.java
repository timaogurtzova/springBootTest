package com.hellocat.springBootTest.repository;

import com.hellocat.springBootTest.domen.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     Optional<User> getUserByName(String name);


}

