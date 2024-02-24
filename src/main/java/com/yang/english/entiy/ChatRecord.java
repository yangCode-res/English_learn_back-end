package com.yang.english.entiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "chat_records")
@Data
@AllArgsConstructor
public class ChatRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_user_id", nullable = false)
    private Long senderUserId;

    @Column(name = "receiver_user_id", nullable = false)
    private Long receiverUserId;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    public ChatRecord() {

    }
    public ChatRecord(Long senderUserId, Long receiverUserId, String message, Timestamp timestamp) {
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.message = message;
        this.timestamp = timestamp;
    }

    // 构造函数、Getter和Setter方法

    // 其他业务逻辑或方法
}
