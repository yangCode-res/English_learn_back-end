package com.yang.english.controller;

import com.yang.english.entiy.Content;
import com.yang.english.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/chapter/{chapterId}")
    public ResponseEntity<List<Content>> getContentsByChapterId(@PathVariable Long chapterId) {
        List<Content> contents = contentService.getContentsByChapterId(chapterId);
        if(contents.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contents);
    }
}
