package ru.devino.template.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.devino.template.dto.TemplateMatchReq;
import ru.devino.template.dto.TemplateMatchRes;
import ru.devino.template.matcher.TemplateMatcher;
import ru.devino.template.matcher.MatchResult;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class TemplateController {

    private final TemplateMatcher templateMatcher;

    public TemplateController(TemplateMatcher templateMatcher) {
        this.templateMatcher = templateMatcher;
    }

    @PostMapping(value = "/match", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public TemplateMatchRes match(@Valid @RequestBody TemplateMatchReq req) {
        MatchResult result = templateMatcher.match(req.getText());
        if (result != null) {
            return TemplateMatchRes.success(req.getText(), result.getTemplate(), result.getParams());
        }
        return TemplateMatchRes.fail(req.getText());
    }

}
