package com.yang.english.controller;

import com.yang.english.entiy.Sentence;
import com.yang.english.entiy.SentenceWithUserInputDTO;
import com.yang.english.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SentenceController {
    private final SentenceService sentenceService;

    @Autowired
    public SentenceController(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @GetMapping("/sentences/{audioId}")
    public List<Sentence> getSentenceByAudioId(@PathVariable Integer audioId) {
        return sentenceService.getSentenceByAudioId(audioId);
    }
    @GetMapping("/sentences/user/{userId}/audio/{audioId}")
    public ResponseEntity<List<SentenceWithUserInputDTO>> getSentencesByUserIdAndAudioId(
            @PathVariable Integer userId,
            @PathVariable Integer audioId) {
        List<SentenceWithUserInputDTO> Dtos=new ArrayList<>();
        List<Object> result = sentenceService.getSentencesByUserIdAndAudioId(userId, audioId);
        for( Object o :result){
            Object[] rowArray=(Object[]) o;

            SentenceWithUserInputDTO dto=new SentenceWithUserInputDTO();
            dto.setId(Long.valueOf((Integer) rowArray[0]));
            dto.setText((String) rowArray[1]);
            dto.setFileId((Integer) rowArray[2]);
            dto.setBeginPosition((Integer) rowArray[3]);
            dto.setEndPosition((Integer) rowArray[4]);
            dto.setTransZh((String) rowArray[5]);
            dto.setAudioId((Integer) rowArray[6]);
            dto.setIsSectionEnd((Boolean) rowArray[7]);
            dto.setWordNumber((Integer) rowArray[8]);
            dto.setIsCollect((Boolean) rowArray[9]);
            dto.setAudioUrlCdn((String) rowArray[10]);
            dto.setUser_input((String) rowArray[11]);
            Dtos.add(dto);
        }
        return new ResponseEntity<>(Dtos, HttpStatus.OK);
    }

}
