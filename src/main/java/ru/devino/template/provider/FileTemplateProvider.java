package ru.devino.template.provider;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

@Component
public class FileTemplateProvider implements TemplateProvider {

    private final List<Template> templates;

    public FileTemplateProvider() throws Exception {
        try (InputStream inputStream = new ClassPathResource("template.txt").getInputStream()) {
            this.templates = unmodifiableList(
                    new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))
                            .lines()
                            .map(Template::new)
                            .collect(toList()));
        }
    }

    @Override
    public String findTemplate(String text) {
        for (Template template : templates) {
            if (template.isMatch(text)) {
                return template.source;
            }
        }
        return null;
    }

    private static class Template {
        private final String source;
        private final Pattern pattern;

        private Template(String source) {
            this.source = source;
            this.pattern = compile(source);
        }

        boolean isMatch(String text) {
            return pattern.matcher(text).find();
        }

    }

    private static Pattern compile(String source) {
        StringBuilder result = new StringBuilder().append("^");

        boolean begin = true;
        for (String part : source.trim().split(Pattern.quote("%w+"), -1)) {
            if (begin) {
                begin = false;
            } else {
                //вместо %w+ допустимы только буквы, цифры и спецсимволы
                result.append("([а-яА-Яa-zA-Z0-9!№#%.,:;?/()+“”_'\"`&^{}\\[\\]<>|@$=~*\\- ]*)");
            }
            result.append(Pattern.quote(part));
        }
        result.append("$");

        return Pattern.compile(result.toString());
    }

}
