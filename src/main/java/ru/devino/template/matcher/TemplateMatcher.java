package ru.devino.template.matcher;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

@Component
public class TemplateMatcher {

    private final List<Template> templates;

    public TemplateMatcher() throws Exception {
        try (InputStream inputStream = new ClassPathResource("template.txt").getInputStream()) {
            this.templates = unmodifiableList(
                    new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))
                            .lines()
                            .map(Template::new)
                            .collect(toList()));
        }
    }

    public MatchResult match(String text) {
        for (Template template : templates) {
            MatchResult result = template.match(text);
            if (result != null) {
                return result;
            }
        }
        return null;
    }


}
