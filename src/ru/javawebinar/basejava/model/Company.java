package ru.javawebinar.basejava.model;

import java.util.Objects;

public class Company {
    private  String place;
    private String url;
    private String date;
    private String position;
    private String aboutPosition;

    public Company(String place, String url, String date, String position, String aboutPosition) {
        this.place = place;
        this.url = url;
        this.date = date;
        this.position = position;
        this.aboutPosition = aboutPosition;
    }

    @Override
    public String toString() {
        return "Company{" +
                "url='" + place + '\'' +
                ", place='" + url + '\'' +
                ", date='" + date + '\'' +
                ", position='" + position + '\'' +
                ", aboutPosition='" + aboutPosition + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(url, company.url) &&
                Objects.equals(place, company.place) &&
                Objects.equals(date, company.date) &&
                Objects.equals(position, company.position) &&
                Objects.equals(aboutPosition, company.aboutPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, place, date, position, aboutPosition);
    }
}
