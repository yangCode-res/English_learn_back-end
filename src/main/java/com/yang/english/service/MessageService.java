package com.yang.english.service;

import com.yang.english.dao.MessageRepository;
import com.yang.english.entiy.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message createMessage(Message message) {
//        message.setSentAt(LocalDateTime.now()); // 设置当前时间
        return messageRepository.save(message);
    }

    public List<Message> getMessageByGroupId(int groupId) {
        return messageRepository.findByGroupIdOrderBySentAtAsc(groupId);
    }
}
