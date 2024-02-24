package com.yang.english.dao;

import com.yang.english.entiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User findByUserId(Integer user_id);
}



