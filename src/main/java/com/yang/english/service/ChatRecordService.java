package com.yang.english.service;

import com.yang.english.dao.ChatRecordRepository;
import com.yang.english.entiy.ChatRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRecordService {
    @Autowired
    private ChatRecordRepository chatRecordRepository;

    public List<ChatRecord> getChatHistory(Long user1Id, Long user2Id) {
        // 查询与两个用户相关的聊天记录并按时间排序
        return chatRecordRepository.findBySenderUserIdAndReceiverUserIdOrSenderUserIdAndReceiverUserIdOrderByTimestamp(
                user1Id, user2Id, user2Id, user1Id);
    }

    public ChatRecord saveChatRecord(ChatRecord chatRecord) {
        // 保存聊天记录到数据库
        return chatRecordRepository.save(chatRecord);
    }
}

