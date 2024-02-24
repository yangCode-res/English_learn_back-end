package com.yang.english.entiy;

import lombok.Data;

@Data
public class FriendInfo {
    private int userId;
    private String nickname;
    private String avatar;

    public FriendInfo(int userId, String nickname, String avatar) {
        this.userId = userId;
        this.nickname = nickname;
        this.avatar = avatar;
    }


}
