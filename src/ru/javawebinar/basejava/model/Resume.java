package ru.javawebinar.basejava.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * ru.javawebinar.basejava.model.Resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;

    private final String fullName;

    public final Map<ContactsType, String> contacts = new EnumMap<>(ContactsType.class);
    public final Map<SectionType, Section> section = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(getUuid(), resume.getUuid()) &&
                Objects.equals(getFullName(), resume.getFullName()) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(section, resume.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getFullName(), contacts, section);
    }

    @Override
    public String toString() {
        return uuid + " " + fullName;
    }

    public String getFullName() {
        return fullName;
    }

}