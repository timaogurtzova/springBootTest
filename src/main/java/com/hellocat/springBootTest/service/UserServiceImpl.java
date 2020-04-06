package com.hellocat.springBootTest.service;

import com.hellocat.springBootTest.domen.User;
import com.hellocat.springBootTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findUserByName(String name) {
        User user = null;
        try {
            user = userRepository.getUserByName(name).get();
        }catch (NoSuchElementException e){

        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long id, User user) {
        User userBD = userRepository.findById(id).get();
        if (userBD != null) {
            user.setPassword(user.getPassword());
            userBD.setName(user.getName());
            userBD.setCity(user.getCity());
            userBD.setAge(user.getAge());
            userBD.setRoles(user.getRoles());
            userRepository.flush();
        }
    }
}
