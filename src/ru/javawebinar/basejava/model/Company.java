package ru.javawebinar.basejava.model;

import java.time.LocalDate;

public class Company {
    private  String name;
    private String url;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String position;
    private String aboutPosition;

    public Company(String name, String url, LocalDate dateFrom, LocalDate dateTo, String position, String aboutPosition) {
        this.name = name;
        this.url = url;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.position = position;
        this.aboutPosition = aboutPosition;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", position='" + position + '\'' +
                ", aboutPosition='" + aboutPosition + '\'' +
                '}';
    }

}
