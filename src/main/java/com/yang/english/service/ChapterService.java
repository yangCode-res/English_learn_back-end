package com.yang.english.service;

import com.yang.english.dao.ChapterRepository;
import com.yang.english.entiy.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    public Optional<Chapter> getChapterById(Long id) {
        return Optional.ofNullable(chapterRepository.findByChapterId(id));
    }
    public List<Chapter> getChaptersByBookId(Long bookId) {
        return chapterRepository.findByBookBookIdOrderByChapterNumber(bookId);
    }
}
