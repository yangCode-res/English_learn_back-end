package com.yang.english.service;

import com.yang.english.dao.SentenceRepository;
import com.yang.english.dao.UserRepository;
import com.yang.english.dao.UserSentencesRepository;
import com.yang.english.entiy.Sentence;
import com.yang.english.entiy.SentenceWithUserInputDTO;
import com.yang.english.entiy.UserSentences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SentenceService {
    @Autowired
    private final SentenceRepository sentenceRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private UserSentencesRepository userSentencesRepository;
    @Autowired
    public SentenceService(SentenceRepository sentenceRepository) {
        this.sentenceRepository = sentenceRepository;
    }

    public List<Sentence> getSentenceByAudioId(Integer audioId) {
        return sentenceRepository.findByAudioId(audioId);
    }
    public List<Object> getSentencesByUserIdAndAudioId(Integer userId, Integer audioId) {
        List<Object> userSentences = userSentencesRepository.findUserSentencesByUserIdAndAudioId(userId, audioId);
        List<Sentence> sentences = sentenceRepository.findByAudioId(audioId);

        return userSentences;
    }
}
