package com.yang.english.service;

import com.yang.english.dao.UserWordsRepository;
import com.yang.english.entiy.Cet4Word;
import com.yang.english.entiy.UserWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    @Autowired
    private UserWordsRepository userWordsRepository;

    public List<Cet4Word> getWordsForReview(Long userId) {

        List<Cet4Word> rememberedWords = userWordsRepository.findRememberedWordsByUserId(userId);

        return rememberedWords;
    }
    public  List<Cet4Word> getWOrdsForNew(Long userId){
        List<Cet4Word> unrememberedWords = userWordsRepository.findUnrememberedWordsByUserId(userId);

        return unrememberedWords;
    }
    public void updateWordAsRemembered(Long userId, Long wordId) {
        userWordsRepository.markWordAsRemembered(userId, wordId);
    }
}
