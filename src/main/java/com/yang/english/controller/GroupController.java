package com.yang.english.controller;

import com.yang.english.entiy.CreateGroupRequest;
import com.yang.english.entiy.Group;
import com.yang.english.entiy.User;
import com.yang.english.entiy.UserGroup;
import com.yang.english.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody CreateGroupRequest request) {
        Group group = groupService.createGroup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }
    @GetMapping("/get/{userId}")
    public ResponseEntity<List<Group>> getGroups(@PathVariable int userId) {
        return ResponseEntity.ok(groupService.findGroupsByUserId(userId));
    }
    @GetMapping("/{groupId}/users")
    public ResponseEntity<Set<User>> getUsersByGroup(@PathVariable Long groupId) {
        Set<User> users = groupService.getUsersByGroupId(groupId);
        return ResponseEntity.ok(users);
    }
    // 其他端点...
}
