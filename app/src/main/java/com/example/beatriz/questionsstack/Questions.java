package com.example.beatriz.questionsstack;

/**
 * Created by Beatriz on 07/04/2016.
 */
public class Questions {
    private String title, imageUser, nameUser, score;

    public Questions(String title, String imageUser, String nameUser, String score){
        this.setTitle(title);
        this.setImageUser(imageUser);
        this.setNameUser(nameUser);
        this.setScore(score);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
