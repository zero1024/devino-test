package ru.devino.template.matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Template {
    private final String template;
    private final Pattern pattern;

    public Template(String template) {
        this.template = template;
        this.pattern = compile(template);
    }

    public MatchResult match(String text) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            List<String> params = new ArrayList<>();
            for (int i = 1; i <= matcher.groupCount(); i++) {
                params.add(matcher.group(i));
            }
            return new MatchResult(template, params);
        }
        return null;
    }


    private static Pattern compile(String template) {
        StringBuilder result = new StringBuilder().append("^");

        boolean begin = true;
        for (String part : template.trim().split(Pattern.quote("%w+"), -1)) {
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
