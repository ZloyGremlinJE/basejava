package ru.javawebinar.phonebook.exception;

    public class ExistStorageException extends StorageException {
        public ExistStorageException(String uuid) {
            super("Contact " + uuid + " already exist", uuid);
        }
    }


