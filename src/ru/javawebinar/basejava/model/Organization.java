package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

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

}
