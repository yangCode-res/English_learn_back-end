package com.yang.english.entiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongDTO {
    private String title;
    private String artistName;
    private String albumTitle;
    private String duration;
    private String lyricsFilePath;
    private String musicFilePath;
    private Long songId;
    // Constructor, Getters, and Setters...
}
