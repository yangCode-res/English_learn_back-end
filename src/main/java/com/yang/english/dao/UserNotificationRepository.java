package com.yang.english.dao;

import com.yang.english.entiy.NotificationDTO;
import com.yang.english.entiy.User;
import com.yang.english.entiy.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserNotificationRepository  extends JpaRepository<UserNotification, Integer> {
    List<UserNotification> findByUserIdAndIsRead(int userId, boolean isRead);

//    @Query("SELECT new com.yang.english.entiy.NotificationDTO(n.user.avatar, n.user.nickname, " +
//            "(SELECT n2.message FROM UserNotification n2 WHERE n2.user.userId = n.user.userId AND n2.createdAt = MAX(n.createdAt)), " +
//            "COUNT(n)) " +
//            "FROM UserNotification n " +
//            "WHERE n.userId = :userId AND n.isRead = false " +
//            "AND n.notificationType IN ('friend_request', 'private_message') " +
//            "GROUP BY n.user.userId, n.user.avatar, n.user.nickname")
//    List<NotificationDTO> findUnreadNotifications(@Param("userId") int userId);
//@Query("SELECT new com.yang.english.entiy.NotificationDTO(n.user.avatar, n.user.nickname, " +
//        "(SELECT n2.message FROM UserNotification n2 WHERE n2.userId = n.userId AND n2.createdAt IN (SELECT MAX(n3.createdAt) FROM com.yang.english.entiy.UserNotification n3 WHERE n3.userId = n2.userId)), " +
//        "COUNT(n), " +
//        "(SELECT n2.notificationType FROM UserNotification n2 WHERE n2.userId = n.userId AND n2.createdAt IN (SELECT MAX(n3.createdAt) FROM com.yang.english.entiy.UserNotification n3 WHERE n3.userId = n2.userId)) ) " +
//        "FROM UserNotification n " +
//        "WHERE n.userId = :userId AND n.isRead = false " +
//        "GROUP BY n.userId, n.user.avatar, n.user.nickname")
//List<NotificationDTO> findUnreadNotifications(@Param("userId") int userId);
@Query("SELECT new com.yang.english.entiy.NotificationDTO(n.user.avatar, n.user.nickname, " +
        "(SELECT n2.message FROM UserNotification n2 WHERE n2.userId = n.userId AND n2.createdAt IN (SELECT MAX(n3.createdAt) FROM UserNotification n3 WHERE n3.userId = n2.userId)), " +
        "COUNT(n), " +
        "(SELECT n2.notificationType FROM UserNotification n2 WHERE n2.userId = n.userId AND n2.createdAt IN (SELECT MAX(n3.createdAt) FROM UserNotification n3 WHERE n3.userId = n2.userId)), " +
        "(SELECT n2.referenceId FROM UserNotification n2 WHERE n2.userId = n.userId AND n2.createdAt IN (SELECT MAX(n3.createdAt) FROM UserNotification n3 WHERE n3.userId = n2.userId)) ) " +
        "FROM UserNotification n " +
        "WHERE n.userId = :userId AND n.isRead = false " +
        "GROUP BY n.userId, n.user.avatar, n.user.nickname")
List<NotificationDTO> findUnreadNotifications(@Param("userId") int userId);


    List<UserNotification> findByReferenceIdAndUserIdAndNotificationType(int referenceId, int userId, String notificationType);


}