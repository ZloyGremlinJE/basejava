package ru.javawebinar.phonebook.storage;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.phonebook.sql.ConnectionFactory;

import java.sql.DriverManager;
import java.util.List;



public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = ()-> DriverManager.getConnection(dbUrl,dbUser,dbPassword);
    }

    @Override
    public void clear() {

    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void save(Resume resume) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public List<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
