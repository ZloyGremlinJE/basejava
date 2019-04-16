package ru.javawebinar.phonebook.storage;

import ru.javawebinar.phonebook.Config;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(Config.get().getSqlStorage());
    }
}