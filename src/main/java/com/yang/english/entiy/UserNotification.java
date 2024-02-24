package com.yang.english.entiy;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_notifications")
@Data
public class UserNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "notification_type", nullable = false)
    private String notificationType;

    @Column(name = "message")
    private String message;


    @Column(name = "reference_id")
    private Integer referenceId;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "reference_id", referencedColumnName = "user_id",insertable = false, updatable = false)
    private User user;


    // 这里省略了构造函数、getter 和 setter 方法

}

