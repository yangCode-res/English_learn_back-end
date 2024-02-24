package com.yang.english.service;

import com.yang.english.dao.UserFriendRepository;
import com.yang.english.entiy.FriendInfo;
import com.yang.english.entiy.UserFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserFriendService {

    @Autowired
    private UserFriendRepository userFriendRepository;

    public UserFriend addFriend(int userId, int friendId) {
        UserFriend userFriend = new UserFriend();
        userFriend.setUserId(userId);
        userFriend.setFriendId(friendId);
        userFriend.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return userFriendRepository.save(userFriend);
    }
    public List<FriendInfo> getUserFriends(int userId) {
        List<Object[]> results = userFriendRepository.findFriendsByUserId(userId);
        List<FriendInfo> friends = new ArrayList<>();
        for (Object[] row : results) {
            int id = (int) row[0];
            String nickname = (String) row[1];
            String avatar = (String) row[2];
            friends.add(new FriendInfo(id, nickname, avatar));
        }
        return friends;
    }
}
