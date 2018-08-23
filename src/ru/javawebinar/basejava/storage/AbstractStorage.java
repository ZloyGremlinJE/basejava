package ru.javawebinar.basejava.storage;


import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void update(Resume r) {
       if (checkForNotExist()){
          throw new NotExistStorageException(r.getUuid());
        }
        doUpdate();
    }

    protected abstract boolean checkForNotExist();
    protected abstract void doUpdate();
}
