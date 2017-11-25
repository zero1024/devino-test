package ru.devino.template.matcher;

import java.util.List;

public class MatchResult {

    private final String template;
    private final List<String> params;

    public MatchResult(String template, List<String> params) {
        this.params = params;
        this.template = template;
    }

    public List<String> getParams() {
        return params;
    }

    public String getTemplate() {
        return template;
    }
}
