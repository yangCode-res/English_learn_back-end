package com.yang.english.entiy;

import lombok.Data;

import java.util.List;

@Data
public class CreateGroupRequest {
    private String groupName;
    private String avatarUrl;
    private List<Integer> userIds;

    // getter 和 setter ...
}
