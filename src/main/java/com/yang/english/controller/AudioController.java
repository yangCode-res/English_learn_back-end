package com.yang.english.controller;



import com.yang.english.dao.AudioRepository;
import com.yang.english.entiy.Audio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/audio")
public class AudioController {

    @Autowired
    private AudioRepository audioRepository;

    @GetMapping("/by-listening-book/{listeningBookId}")
    public List<Audio> getAudiosByListeningBookId(@PathVariable Long listeningBookId) {
        return audioRepository.findByListeningBookId(listeningBookId);
    }
}
