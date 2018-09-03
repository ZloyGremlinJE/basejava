package ru.javawebinar.basejava.storage;


import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void update(Resume resume) {
        // get SearchKey and check for not exist
        int searchKey = checkForNotExist(resume.getUuid());
        //update
        doUpdate(searchKey, resume);
    }

    @Override
    public void save(Resume resume) {
        // get searchKey and check for exist
        int searchKey = checkForExist(resume.getUuid());
        //save
        doSave(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        // get searchKey and check for not exist
        int searchKey = checkForNotExist(uuid);
        //get
        return doGet(searchKey, uuid);
    }

    @Override
    public void delete(String uuid) {
        // get searchKey and check for not exist
        int searchKey = checkForNotExist(uuid);
        // delete
        doDelete(searchKey, uuid);

    }

    protected int checkForExist(String uuid) {
        int searchKey = getSearchKey(uuid);
        if (searchKey >= 0) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected int checkForNotExist(String uuid) {
        int searchKey = getSearchKey(uuid);
        if (searchKey < 0) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    abstract protected void doUpdate(int searchKey, Resume resume);

    abstract protected void doSave(int searchKey, Resume resume);

    abstract protected int getSearchKey(String uuid);

    abstract protected Resume doGet(int searchKey, String uuid);

    abstract protected void doDelete(int searchKey, String uuid);
}
