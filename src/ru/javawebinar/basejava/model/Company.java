package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) &&
                Objects.equals(url, company.url) &&
                Objects.equals(dateFrom, company.dateFrom) &&
                Objects.equals(dateTo, company.dateTo) &&
                Objects.equals(position, company.position) &&
                Objects.equals(aboutPosition, company.aboutPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, dateFrom, dateTo, position, aboutPosition);
    }
}
