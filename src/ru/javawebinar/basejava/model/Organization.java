package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;



public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private  Link homepage;

    private List<PlaceDescription> placeDescriptions;

    public Organization() {
    }

    public Organization(String name, String url, PlaceDescription... placeDescriptions) {
        this(new Link(name, url), Arrays.asList(placeDescriptions));
    }

    public Organization(Link homepage, List<PlaceDescription> placeDescriptions) {
        this.homepage = homepage;
        this.placeDescriptions = placeDescriptions;
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


    public static class PlaceDescription implements Serializable{
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;

        public PlaceDescription(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PlaceDescription that = (PlaceDescription) o;
            return Objects.equals(startDate, that.startDate) &&
                    Objects.equals(endDate, that.endDate) &&
                    Objects.equals(title, that.title) &&
                    Objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }

        @Override
        public String toString() {
            return "PlaceDescription{" + startDate + ", " + endDate + ", " + title + ", " + description + '}' + "\n";
        }
    }
}
