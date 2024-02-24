package com.yang.english.entiy;



import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "listening_books")
@Data
public class ListeningBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "parent_id")
    private Long parentId;

    // standard getters and setters
}
