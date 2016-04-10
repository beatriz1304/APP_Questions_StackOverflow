package com.example.beatriz.questionsstack;

import android.widget.Button;

/**
 * Created by Beatriz on 10/04/2016.
 */
public class ButtonInfo {

    protected String name;
    protected int image;
    protected int id;


    protected Button buttonId;

    public Button getButtonId() {
        return buttonId;
    }

    public void setButtonId(Button buttonId) {
        this.buttonId = buttonId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

