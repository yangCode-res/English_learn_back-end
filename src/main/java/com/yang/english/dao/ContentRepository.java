package com.yang.english.dao;

import com.yang.english.entiy.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByChapter_ChapterIdOrderBySequence(Long chapterId);
}
