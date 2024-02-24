package com.yang.english.entiy;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "artists")
@Data
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;

    @Column(nullable = false)
    private String name;

    // Getters and setters...
}
