package com.yang.english.entiy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user_table")
@Data
@ToString(exclude = "userGroups")
@EqualsAndHashCode(exclude = "userGroups")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;
    private String username;
    private String password;
    private int status;
    private String nickname;
    private String phone;
    private String email;
    private String avatar;
    private int user_level_id;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<UserGroup> userGroups;
}
