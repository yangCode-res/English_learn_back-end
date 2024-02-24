package com.yang.english.entiy;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_friends")
@Data
public class UserFriend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "friend_id", nullable = false)
    private int friendId;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
