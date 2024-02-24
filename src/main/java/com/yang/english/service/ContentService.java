package com.yang.english.service;

import com.yang.english.dao.ContentRepository;
import com.yang.english.entiy.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public List<Content> getContentsByChapterId(Long chapterId) {
        return contentRepository.findByChapter_ChapterIdOrderBySequence(chapterId);
    }
}
