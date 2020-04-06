package com.hellocat.springBootTest.service;

import com.hellocat.springBootTest.domen.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findUserById(Long id);

    User findUserByName(String name);

    void saveUser(User user);

    void deleteUser(Long id);

    void updateUser(Long is, User user);
}
