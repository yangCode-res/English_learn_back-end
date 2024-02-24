package com.yang.english.controller;

import com.yang.english.entiy.ChatRecord;
import com.yang.english.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private ChatRecordService chatRecordService;

    @GetMapping("/history")
    public List<ChatRecord> getChatHistory(@RequestParam("user1Id") Long user1Id,
                                           @RequestParam("user2Id") Long user2Id) {
        // 调用服务层获取聊天记录
        return chatRecordService.getChatHistory(user1Id, user2Id);
    }
    @PostMapping("/store")
    public ChatRecord storeChatRecord(@RequestBody ChatRecord chatRecord) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        chatRecord.setTimestamp(timestamp);
        // 调用服务层保存聊天记录
        return chatRecordService.saveChatRecord(chatRecord);
    }
}

