package ru.javawebinar.phonebook.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Contact {
    // Unique identifier
    private String uuid;
    private String fullName;
    private ArrayList<String> phoneNumbers;
    private String department;

    public Contact(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Contact(String uuid, String fullName, ArrayList<String> phoneNumbers, String department) {
        this(uuid,fullName);
        this.phoneNumbers = phoneNumbers;
        this.department = department;
    }

    public Contact(String fullName){ this(UUID.randomUUID().toString(), fullName); }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public String getPhoneNumbers(String split){
        for (String s: phoneNumbers
             ) {

        }
        return null;
    }

    public String getDepartment() {
        return department;
    }

    public  void addPhoneNumber (String number){
        phoneNumbers.add(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!uuid.equals(contact.uuid)) return false;
        if (!fullName.equals(contact.fullName)) return false;
        if (!Objects.equals(phoneNumbers, contact.phoneNumbers))
            return false;
        return Objects.equals(department, contact.department);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + (phoneNumbers != null ? phoneNumbers.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return uuid + ", " + fullName;
    }
}
