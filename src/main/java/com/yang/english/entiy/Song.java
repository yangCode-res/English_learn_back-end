package com.yang.english.entiy;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Songs")
@Data
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @Column
    private String duration;

    @Column
    private String lyricsFilePath;

    @Column
    private String musicFilePath;

    // Getters and setters...
}

