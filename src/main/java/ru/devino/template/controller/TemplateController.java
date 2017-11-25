package ru.devino.template.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.devino.template.dto.TemplateMatchReq;
import ru.devino.template.dto.TemplateMatchRes;
import ru.devino.template.provider.FileTemplateProvider;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class TemplateController {

    private final FileTemplateProvider service;

    public TemplateController(FileTemplateProvider service) {
        this.service = service;
    }

    @PostMapping(value = "/match", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public TemplateMatchRes match(@Valid @RequestBody TemplateMatchReq req) {
        String template = service.findTemplate(req.getText());
        return new TemplateMatchRes(
                template != null,
                req.getText(),
                template
        );
    }

}
