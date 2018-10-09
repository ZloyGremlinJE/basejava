package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class WorkStudySection extends Section {
   private  String place;
   private String date;
   private String position;
   private String aboutPosition;
   public final List<String>  list = new ArrayList<>();

    public WorkStudySection(String place, String date, String position, String aboutPosition) {
        this.place = place;
        this.date = date;
        this.position = position;
        this.aboutPosition = aboutPosition;
        list.add(place + " " + date + " " + position + " " + aboutPosition);
    }

    @Override
    public String toString() {
        return  list.toString();
    }
}
