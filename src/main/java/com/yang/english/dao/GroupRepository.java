package com.yang.english.dao;

import com.yang.english.entiy.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("SELECT g FROM Group g join fetch g.userGroups ug WHERE ug.user.userId = :userId")
    List<Group> findGroupsByUserId(@Param("userId") int userId);
    Group findByGroupId(Long groupId);
}
