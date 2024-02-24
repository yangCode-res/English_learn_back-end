package com.yang.english.service;

import com.yang.english.dao.UserRepository;
import com.yang.english.entiy.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @Transactional
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User LookUserByID(int id) {
        return userRepository.findByUserId(id);
    }

    public User LookUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUser(int userId, User updatedUser) {
                User user=userRepository.findByUserId(userId);
                user.setAvatar(updatedUser.getAvatar());
                user.setEmail(updatedUser.getEmail());
                user.setNickname(updatedUser.getNickname());
                user.setPhone(updatedUser.getPhone());
                return userRepository.save(user);
    }

}
