package ru.javawebinar.phonebook.storage;

import ru.javawebinar.phonebook.model.Contact;

import java.util.List;

public interface Storage {
    void clear();

    void update(Contact contact);

    void save(Contact contact);

    Contact get(String uuid);

    void delete(String uuid);

    List<Contact> getAllSorted();

    int size();

}
