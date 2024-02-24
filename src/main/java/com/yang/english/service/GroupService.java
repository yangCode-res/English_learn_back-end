package com.yang.english.service;

import com.yang.english.dao.GroupRepository;
import com.yang.english.dao.UserGroupRepository;
import com.yang.english.dao.UserRepository;
import com.yang.english.entiy.CreateGroupRequest;
import com.yang.english.entiy.Group;
import com.yang.english.entiy.User;
import com.yang.english.entiy.UserGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;
    // 创建群组的方法
    public Group createGroup(CreateGroupRequest request) {
        Group group = new Group();
        group.setGroupName(request.getGroupName());
        group.setAvatar(request.getAvatarUrl());

        Group savedGroup = groupRepository.save(group);

        for (Integer userId : request.getUserIds()) {
            UserGroup userGroup = new UserGroup();
            User user = userRepository.findByUserId(userId);
            userGroup.setUser(user);
            userGroup.setGroup(savedGroup);
            // 保存 UserGroup 实体，确保关系得到持久化
            log.info(String.valueOf(savedGroup.getGroupId()));
            userGroupRepository.save(userGroup);
        }

        return savedGroup;
    }

    public List<Group> findGroupsByUserId(int userId) {
        return groupRepository.findGroupsByUserId(userId);
    }
    @Transactional
    public Set<User> getUsersByGroupId(Long groupId) {
        Group group = groupRepository.findByGroupId(groupId);
        return group.getUserGroups().stream()
                .map(UserGroup::getUser)
                .collect(Collectors.toSet());
    }

    // 其他方法...
}
