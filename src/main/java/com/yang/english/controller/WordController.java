package com.yang.english.controller;

import com.yang.english.dao.Cet4Repository;
import com.yang.english.dao.WordRepository;
import com.yang.english.entiy.*;
import com.yang.english.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WordController {
    @Autowired
    private WordService wordService;

    @GetMapping("/user/{userId}/review-words")
    public Response getReviewWords(@PathVariable Long userId) {
        List<Cet4Word> rewWords=wordService.getWordsForReview(userId);
        List<Cet4Word> neWords=wordService.getWOrdsForNew(userId);
        return new Response("ok",new WordData<>(neWords,rewWords));
    }
    @PostMapping("/user/{userId}/words/{wordId}/remember")
    public ResponseEntity<?> markWordAsRemembered(@PathVariable Long userId, @PathVariable Long wordId) {
        wordService.updateWordAsRemembered(userId, wordId);
        return ResponseEntity.ok().build();
    }

}
