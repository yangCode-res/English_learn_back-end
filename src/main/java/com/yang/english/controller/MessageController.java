package com.yang.english.controller;

import com.yang.english.entiy.Message;
import com.yang.english.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageService.createMessage(message));
    }
    @GetMapping("/get/{groupId}")
    public ResponseEntity<List<Message>> getMessageByGroupId(@PathVariable int groupId) {
        return ResponseEntity.ok(messageService.getMessageByGroupId(groupId));
    }
}
