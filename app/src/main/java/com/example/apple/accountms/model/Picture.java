package com.example.apple.accountms.model;

/**
 * Created by apple on 2017/12/27.
 */

public class Picture {
    private String title;
    private int imageId;

    public Picture() {
    }

    public Picture(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
