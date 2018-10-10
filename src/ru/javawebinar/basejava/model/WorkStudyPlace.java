package ru.javawebinar.basejava.model;

public class WorkStudyPlace {
    private  String place;
    private String date;
    private String position;
    private String aboutPosition;

    public WorkStudyPlace(String place, String date, String position, String aboutPosition) {
        this.place = place;
        this.date = date;
        this.position = position;
        this.aboutPosition = aboutPosition;
    }

    @Override
    public String toString() {
        return "WorkStudyPlace{" +
                "place='" + place + '\'' +
                ", date='" + date + '\'' +
                ", position='" + position + '\'' +
                ", aboutPosition='" + aboutPosition + '\'' +
                '}';
    }
}
