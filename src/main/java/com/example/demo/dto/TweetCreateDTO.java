package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TweetCreateDTO {
    @NotNull
    @NotEmpty
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
