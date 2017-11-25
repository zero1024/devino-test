package ru.devino.template.dto;

import java.util.List;

public class TemplateMatchRes {

    private final boolean success;
    private final String sourceText;
    private final String template;
    private final List<String> params;

    public static TemplateMatchRes success(String sourceText, String template, List<String> params) {
        return new TemplateMatchRes(true, sourceText, template, params);
    }

    public static TemplateMatchRes fail(String sourceText) {
        return new TemplateMatchRes(false, sourceText, null, null);
    }

    private TemplateMatchRes(boolean success, String sourceText, String template, List<String> params) {
        this.success = success;
        this.sourceText = sourceText;
        this.template = template;
        this.params = params;
    }

    public List<String> getParams() {
        return params;
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
