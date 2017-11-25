package ru.devino.test.dto;

import javax.validation.constraints.NotNull;

public class TemplateMatchReq {

    @NotNull
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
