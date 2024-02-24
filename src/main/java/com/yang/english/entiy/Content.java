package com.yang.english.entiy;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Contents")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId;

    @ManyToOne
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

    @Column(nullable = false)
    private Integer sequence;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
// 省略getter和setter方法
}
