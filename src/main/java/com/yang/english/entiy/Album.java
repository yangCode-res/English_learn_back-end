package com.yang.english.entiy;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "albums")
@Data
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long albumId;

    @Column(nullable = false)
    private String title;

    @Column
    private Date releaseDate;

    // Getters and setters...
}
