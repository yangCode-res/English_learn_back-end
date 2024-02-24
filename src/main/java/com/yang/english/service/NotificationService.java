package com.yang.english.service;

import com.yang.english.dao.UserNotificationRepository;
import com.yang.english.entiy.NotificationDTO;
import com.yang.english.entiy.UserNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private UserNotificationRepository userNotificationRepository;

    public void createNotification(int userId, String type, String message, Integer referenceId) {
        UserNotification notification = new UserNotification();
        notification.setUserId(userId);
        notification.setNotificationType(type);
        notification.setMessage(message);
        notification.setReferenceId(referenceId);
        notification.setRead(false);
        userNotificationRepository.save(notification);
    }
    public List<NotificationDTO> getUnreadNotifications(int userId) {
        return userNotificationRepository.findUnreadNotifications(userId);
    }
    public void markAsReadByReferenceIdAndUserIdAndType(int referenceId, int userId, String notificationType) {
        List<UserNotification> notifications = userNotificationRepository.findByReferenceIdAndUserIdAndNotificationType(referenceId, userId, notificationType);
        notifications.forEach(n -> {
            n.setRead(true);
            userNotificationRepository.save(n);
        });
    }

}
