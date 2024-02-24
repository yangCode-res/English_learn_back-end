package com.yang.english.dao;

import com.yang.english.entiy.ChatRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRecordRepository extends JpaRepository<ChatRecord, Long> {
    // 根据条件查询聊天记录，可以自定义查询方法
    List<ChatRecord> findBySenderUserIdAndReceiverUserIdOrSenderUserIdAndReceiverUserIdOrderByTimestamp(
            Long senderUserId1, Long receiverUserId1, Long senderUserId2, Long receiverUserId2);
}