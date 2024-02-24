package com.yang.english.dao;

import com.yang.english.entiy.Chapter;
import com.yang.english.entiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    Chapter findByChapterId(Long chapterId);
    List<Chapter> findByBookBookIdOrderByChapterNumber(Long bookId);
}
