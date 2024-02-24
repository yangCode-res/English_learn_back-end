package com.yang.english.dao;

import com.yang.english.entiy.Cet4Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cet4Repository extends JpaRepository<Cet4Word, Integer> {
    // 可以在这里添加自定义的查询方法
}
