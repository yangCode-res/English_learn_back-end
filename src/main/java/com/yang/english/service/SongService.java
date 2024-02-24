package com.yang.english.service;

import com.yang.english.dao.SongRepository;
import com.yang.english.entiy.Song;
import com.yang.english.entiy.SongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public List<SongDTO> getAllSongs() {
        return songRepository.findAll().stream().map(song -> {
            SongDTO dto = new SongDTO();
            dto.setSongId(song.getSongId());
            dto.setTitle(song.getTitle());
            dto.setArtistName(song.getArtist().getName());
            dto.setAlbumTitle(song.getAlbum().getTitle());
            dto.setDuration(song.getDuration());
            dto.setLyricsFilePath(song.getLyricsFilePath());
            dto.setMusicFilePath(song.getMusicFilePath());
            // ... set other fields ...
            return dto;
        }).collect(Collectors.toList());
    }
public Song getSongById(Long id) {
        return songRepository.findBySongId(id);
}
    // 其他业务逻辑
}
