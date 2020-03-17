package com.example.zzl.LaoBan.Bean;

import android.graphics.Bitmap;


public class Supermarket {
    private String title;
    private Bitmap image;
    private String url;
    private String date;
    private String source;

    public Supermarket(Bitmap image, String title, String url, String date, String source) {
        this.image = image;
        this.title = title;
        this.url = url;
        this.date = date;
        this.source = source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public String getTitle() {
        return title;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }


}
