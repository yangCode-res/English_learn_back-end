package com.yang.english.entiy;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
public class SentenceWithUserInputDTO {
    private Long id;
    private String text;
    private Integer fileId;
    private Integer beginPosition;
    private Integer endPosition;
    private String transZh;
    private Integer audioId;
    private Boolean isSectionEnd;
    private Integer wordNumber;
    private Boolean isCollect;
    private String audioUrlCdn;
    private String user_input;

    // 构造函数和 getter/setter 方法
}

