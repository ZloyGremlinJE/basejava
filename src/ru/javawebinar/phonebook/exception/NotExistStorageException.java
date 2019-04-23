package ru.javawebinar.phonebook.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Contact " + uuid + " not exist", uuid);
    }
}
