package com.yang.english.entiy;
import lombok.Data;

import javax.persistence.*;

@Entity(name="sentences")
@Data
public class Sentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(name = "file_id", nullable = false)
    private Integer fileId;

    @Column(name = "begin_position", nullable = false)
    private Integer beginPosition;

    @Column(name = "end_position", nullable = false)
    private Integer endPosition;

    @Column(name = "trans_zh", nullable = false, columnDefinition = "TEXT")
    private String transZh;

    @Column(name = "audio_id", nullable = false)
    private Integer audioId;

    @Column(name = "is_section_end", nullable = false)
    private Boolean isSectionEnd;

    @Column(name = "word_number", nullable = false)
    private Integer wordNumber;

    @Column(name = "is_collect", nullable = false)
    private Boolean isCollect;

    @Column(name = "audio_url_cdn", nullable = false, columnDefinition = "TEXT")
    private String audioUrlCdn;

    // getters and setters
}