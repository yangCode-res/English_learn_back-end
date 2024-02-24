package com.yang.english.entiy;

import java.util.List;

public class WordData<T> {
    private List<T> newWords;
    private List<T> reviewWords;
    // 构造函数、getter和setter
    public WordData( List newWords,List reviewWords) {
        this.newWords = newWords;
        this.reviewWords=reviewWords;
    }

    public List<T> getNewWords() {
        return this.newWords;
    }
    public List<T> getReviewWords(){
        return this.reviewWords;
    }

    public void setToken( List newWords,List reviewWords) {
        this.newWords = newWords;
        this.reviewWords=reviewWords;
    }
}
