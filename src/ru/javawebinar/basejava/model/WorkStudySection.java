package ru.javawebinar.basejava.model;

import java.util.List;

public class WorkStudySection extends Section {
   protected List<WorkStudyPlace>  list;

    public WorkStudySection(List<WorkStudyPlace> list) {
        this.list = list;

    }

    @Override
    public String toString() {
        return  list.toString();
    }
}
