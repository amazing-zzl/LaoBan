package com.example.zzl.LaoBan.Bean;


import android.graphics.Bitmap;

public class Question {

    private Integer questionId;
    private String questionTitle;
    private String questionContent;
    private Bitmap questionPicture;
    private String updateTime;


    public Question(Integer questionId, String questionTitle, String questionContent, Bitmap questionPicture, String updateTime) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.questionPicture = questionPicture;
        this.updateTime = updateTime;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Bitmap getQuestionPicture() {
        return questionPicture;
    }

    public void setQuestionPicture(Bitmap questionPicture) {
        this.questionPicture = questionPicture;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }


}
