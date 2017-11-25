package ru.devino.test.dto;

public class TemplateMatchRes {

    private final boolean success;
    private final String sourceText;
    private final String template;

    public TemplateMatchRes(boolean success, String sourceText, String template) {
        this.success = success;
        this.sourceText = sourceText;
        this.template = template;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getSourceText() {
        return sourceText;
    }

    public String getTemplate() {
        return template;
    }

}
