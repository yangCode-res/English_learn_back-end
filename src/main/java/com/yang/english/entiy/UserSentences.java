package com.yang.english.entiy;


import lombok.Data;

import javax.persistence.*;

@Entity(name = "user_sentences")
@Data
public class UserSentences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "audio_id")
    private Audio audio;

    @ManyToOne
    @JoinColumn(name = "sentences_id")
    private Sentence sentence;

    private String user_input;

    // other fields, getters, and setters
}
