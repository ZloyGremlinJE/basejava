package ru.javawebinar.basejava.storage;


import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void update(Resume resume) {
        // get searchIndex and check for not exist
        int searchIndex = getSearchKey(resume.getUuid());
        if (searchIndex < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        //update
        doUpdate(searchIndex, resume);
    }

    @Override
    public void save(Resume resume) {
        // get searchIndex and check for exist
        int searchIndex = getSearchKey(resume.getUuid());
        if (searchIndex >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        //save
        doSave(searchIndex, resume);
    }

    @Override
    public Resume get(String uuid) {
        // get searchIndex and check for not exist
        int searchIndex = getSearchKey(uuid);
        if (searchIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        //get
        return doGet(searchIndex, uuid);
    }

    @Override
    public void delete(String uuid) {
        // get searchIndex and check for not exist
        int searchIndex = getSearchKey(uuid);
        if (searchIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        // delete
        doDelete(searchIndex, uuid);

    }

    abstract protected void doUpdate(int searchIndex, Resume resume);

    abstract protected void doSave(int searchIndex, Resume resume);

    abstract protected int getSearchKey(String uuid);

    abstract protected Resume doGet(int searchIndex, String uuid);

    abstract protected void doDelete(int searchIndex, String uuid);
}
