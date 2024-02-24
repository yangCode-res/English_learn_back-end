package com.yang.english.entiy;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity(name="cet4")
@Data
public class Cet4Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int word_id;
    private String Word;
    private String N;
    private String V;
    private String ADJ;
    private String ADV;
    private String PREP;
    private String CONJ;
    private String PRON;
    private String INTERJ;
    private String VI;
    private String VT;
    private Boolean isOK;
    private String PronunciationURL;
    private String UKPhonetic;
    private String USPhonetic;

}
