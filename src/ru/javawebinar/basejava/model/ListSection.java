package ru.javawebinar.basejava.model;

import java.util.List;

public class ListSection extends Section  {
   protected List<String> list;

    public ListSection(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return  list.toString();
    }
}
