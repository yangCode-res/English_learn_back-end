package com.yang.english.dao;

import com.yang.english.entiy.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    // 你可以在这里添加自定义查询方法
    Song findBySongId(Long songId);
}
