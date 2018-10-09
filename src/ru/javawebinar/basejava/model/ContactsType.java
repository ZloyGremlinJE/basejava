package ru.javawebinar.basejava.model;

public enum ContactsType {
    PHONE("Тел.:"),
    SKYPE("Skype:"),
    EMAIL("Почта:"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private String title;

    ContactsType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
