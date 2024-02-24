package com.yang.english.entiy;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class NotificationDTO {
    private String avatar;
    private String nickname;
    private String latestMessage;
    private long unreadCount;
    private String notificationType; // 新增字段
    private int referenceId;         // 新增字段
    // 构造方法
    public NotificationDTO(String avatar, String nickname, String latestMessage, long unreadCount, String notificationType, int referenceId) {
        this.avatar = avatar;
        this.nickname = nickname;
        this.latestMessage = latestMessage;
        this.unreadCount = unreadCount;
        this.notificationType = notificationType;
        this.referenceId = referenceId;
    }
}
