package com.yang.english.entiy;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "message_table")
public class Message {
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "group_id", nullable = false)
    private int groupId;

    @Column(nullable = false)
    private String content;

    @Column(name = "sent_at")
    private Timestamp sentAt;
    @PrePersist
    protected void onCreate() {
        if (sentAt == null) {
            sentAt = Timestamp.valueOf(LocalDateTime.now());
        }
    }

//    @PrePersist
//    protected void onCreate() {
//        sentAt = LocalDateTime.now();
//    }
    // 构造方法，getter 和 setter 等
}
