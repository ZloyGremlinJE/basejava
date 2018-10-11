package ru.javawebinar.basejava.model;

import java.util.Objects;

public class SimpleTextSection extends Section {
    private String text;

    public SimpleTextSection(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleTextSection that = (SimpleTextSection) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    public String toString() {
        return text;
    }
}
