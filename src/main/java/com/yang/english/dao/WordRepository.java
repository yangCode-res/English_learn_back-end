package com.yang.english.dao;

import com.yang.english.entiy.words;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<words, Integer> {
    // 可以在这里添加自定义的查询方法
}
