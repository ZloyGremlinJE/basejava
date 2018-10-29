package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homepage;

    private final List<PlaceDescription> placeDescriptions = new ArrayList<>();

    public Organization(String name, String url) {
        this.homepage = new Link(name, url);
    }

    public void addPlaceDescription(PlaceDescription placeDescription) {
        placeDescriptions.add(placeDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homepage, that.homepage) &&
                Objects.equals(placeDescriptions, that.placeDescriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homepage, placeDescriptions);
    }

    @Override
    public String toString() {
        return "Organization{" + homepage + ", " + placeDescriptions + '}';
    }
}
