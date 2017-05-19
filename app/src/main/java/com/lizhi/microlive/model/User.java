package com.lizhi.microlive.model;

/**
 * Created by Administrator on 2017/5/14.
 */

public class User {

    private String faceUrl;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }
}
