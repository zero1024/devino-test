package ru.devino.template.provider;

public interface TemplateProvider {
    /**
     * @return null, если шаблон не найден.
     */
    String findTemplate(String text);
}
