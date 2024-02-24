package com.yang.english.controller;

import com.yang.english.entiy.Chapter;
import com.yang.english.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/getchapters/{id}")
    public ResponseEntity<?> getChapterById(@PathVariable Long id) {
        return chapterService.getChapterById(id)
                .map(chapter -> ResponseEntity.ok().body(chapter))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Chapter>> getChaptersByBookId(@PathVariable Long bookId) {
        List<Chapter> chapters = chapterService.getChaptersByBookId(bookId);
        if (chapters.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(chapters);
    }
}
