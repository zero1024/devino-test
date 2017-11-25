package ru.devino.test.service;

public interface TemplateProvider {
    /**
     * @return null, если шаблон не найден.
     */
    String findTemplate(String text);
}
