package com.example.zzl.LaoBan.Bean;

import android.graphics.Bitmap;

public class Answer {
    private Integer answerId;
    private Integer questionId;
    private int imageId;
    private String answerContent;
    private Bitmap answerPicture;
    private String updateTime;

    public Answer(Integer answerId, Integer questionId, String answerContent, Bitmap answerPicture, String updateTime) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.answerContent = answerContent;
        this.answerPicture = answerPicture;
        this.updateTime = updateTime;
    }

    public Answer(String answerContent, int imageId) {
        this.answerContent = answerContent;
        this.imageId = imageId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Bitmap getAnswerPicture() {
        return answerPicture;
    }

    public void setAnswerPicture(Bitmap answerPicture) {
        this.answerPicture = answerPicture;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
