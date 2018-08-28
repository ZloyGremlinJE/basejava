package ru.javawebinar.basejava.storage;


import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void update(Resume r) {
        //check for exist
        if(!checkForExist(r)){
            throw new ExistStorageException(r.getUuid());
        }
        //update
        doUpdate(r);
    }
    abstract protected boolean checkForExist(Resume r);
    abstract protected void doUpdate(Resume r);
}
