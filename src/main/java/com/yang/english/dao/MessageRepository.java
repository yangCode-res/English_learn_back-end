package com.yang.english.dao;

import com.yang.english.entiy.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByGroupIdOrderBySentAtAsc(int groupId);
}
