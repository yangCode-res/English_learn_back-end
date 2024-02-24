package com.yang.english.controller;

import com.yang.english.entiy.FriendInfo;
import com.yang.english.entiy.NotificationDTO;
import com.yang.english.entiy.UserFriend;
import com.yang.english.service.NotificationService;
import com.yang.english.service.UserFriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.hutool.core.lang.Console.log;

@RestController
@RequestMapping("/api/friends")
@Slf4j
public class UserFriendsController {

    @Autowired
    private UserFriendService userFriendService;
    @Autowired
    private  NotificationService notificationService;
    @PostMapping("/add")
    public ResponseEntity<UserFriend> addFriend(@RequestParam int userId, @RequestParam int friendId) {
        UserFriend userFriend = userFriendService.addFriend(userId, friendId);
        return ResponseEntity.ok(userFriend);
    }

    @PostMapping("/request")
    public ResponseEntity<String> sendFriendRequest(@RequestParam int requesterId, @RequestParam int recipientId) {
        // 假设在这里处理好友请求的逻辑
        // ...
        // 在通知表中创建一条记录

        notificationService.createNotification(recipientId, "friend_request", "You have a new friend request from user " + requesterId, requesterId);

        return ResponseEntity.ok("Friend request sent successfully");
    }
    @GetMapping("/unread")
    public ResponseEntity<List<NotificationDTO>> getUnreadNotifications(@RequestParam int userId) {
        List<NotificationDTO> notifications = notificationService.getUnreadNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/markAsRead")
    public ResponseEntity<?> markNotificationsAsRead(@RequestParam int referenceId, @RequestParam int userId, @RequestParam String notificationType) {
        notificationService.markAsReadByReferenceIdAndUserIdAndType(referenceId, userId, notificationType);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<FriendInfo>> getUserFriends(@PathVariable int userId) {
        List<FriendInfo> friends = userFriendService.getUserFriends(userId);
        return ResponseEntity.ok(friends);
    }

}
