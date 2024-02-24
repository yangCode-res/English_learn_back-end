package com.yang.english.dao;

import com.yang.english.entiy.FriendInfo;
import com.yang.english.entiy.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserFriendRepository extends JpaRepository<UserFriend, Integer> {
    // 这里可以添加自定义的查询方法
    @Query(value = "SELECT u.user_id, u.nickname, u.avatar " +
            "FROM user_table u " +
            "JOIN user_friends uf ON u.user_id = uf.friend_id " +
            "WHERE uf.user_id = :userId", nativeQuery = true)
    List<Object[]> findFriendsByUserId(@Param("userId") int userId);


}

