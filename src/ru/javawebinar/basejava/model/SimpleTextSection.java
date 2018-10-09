package ru.javawebinar.basejava.model;

public class SimpleTextSection extends Section {
    private String text;

    public SimpleTextSection(String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }
}
