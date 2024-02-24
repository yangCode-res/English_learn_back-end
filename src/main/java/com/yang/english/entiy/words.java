package com.yang.english.entiy;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class words {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String word;
    private String meaning;
    private Boolean isok;
    private String Apronunciation;
    private String Epronunciation;
    private String nmean;
    private String vmean;
    private String audiourl;

    // 构造函数、getter和setter
    // 省略了具体实现以保持简洁
}
