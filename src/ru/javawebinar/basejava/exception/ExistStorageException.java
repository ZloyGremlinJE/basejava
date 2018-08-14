package ru.javawebinar.basejava.exception;

public class ExistStorageException extends ru.javawebinar.basejava.exception.StorageException {
    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " already exist", uuid);
    }
}
