package com.yang.english.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "group_table")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "userGroups")
@EqualsAndHashCode(exclude = "userGroups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    private String groupName;
    // 新添加的头像 URL 属性
    @Column(name = "avatar")
    private String avatar;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "group",cascade = CascadeType.PERSIST)
    private Set<UserGroup> userGroups = new HashSet<>();
    // 其他 getter 和 setter 方法...
}
