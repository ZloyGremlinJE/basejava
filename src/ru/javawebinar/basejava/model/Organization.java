package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homepage;

     private final List<DescriptPlace> descriptPlaces = new ArrayList<>();

    public Organization(String name, String url) {
        Link homepage = new Link(name, url);
        this.homepage = homepage;
    }

    public void addDescriptPlace(DescriptPlace descriptPlace){
       descriptPlaces.add(descriptPlace);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homepage, that.homepage) &&
                Objects.equals(descriptPlaces, that.descriptPlaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homepage, descriptPlaces);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homepage=" + homepage +
                ", descriptPlaces=" + descriptPlaces +
                '}';
    }
}
