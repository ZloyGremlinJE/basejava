package ru.javawebinar.basejava.exception;

public class NotExistStorageException extends ru.javawebinar.basejava.exception.StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + " not exist", uuid);
    }
}
