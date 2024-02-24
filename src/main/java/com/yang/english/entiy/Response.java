package com.yang.english.entiy;

// Response.java
public class Response {
    private String msg;
    private ResponseData data;
    private WordData wordData;

    // 构造函数、getter和setter
    public Response(String msg, ResponseData data) {
        this.msg = msg;
        this.data = data;
    }
    public Response(String msg,WordData wordData){
        this.msg=msg;
        this.wordData=wordData;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseData getData() {
        return data;
    }
    public WordData getWordData(){
        return wordData;
    }
    public void setWordData(WordData wordData){
        this.wordData=wordData;
    }
    public void setData(ResponseData data) {
        this.data = data;
    }
}