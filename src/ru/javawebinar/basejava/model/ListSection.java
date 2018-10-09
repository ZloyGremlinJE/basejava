package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends Section  {
   public List<String> list = new ArrayList<>();

    public ListSection(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return  list.toString();
    }
}
