package ru.javawebinar.basejava.storage;


import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    abstract protected boolean checkExist(Object searchKey);

    abstract protected void doUpdate(Object searchKey, Resume resume);

    abstract protected void doSave(Object searchKey, Resume resume);

    abstract protected Object getSearchKey(String uuid);

    abstract protected Resume doGet(Object searchKey);

    abstract protected void doDelete(Object searchKey);

    @Override
    public void update(Resume resume) {
        // get SearchKey and check for not exist
        Object searchKey = getExistKey(resume.getUuid());
        //update
        doUpdate(searchKey, resume);
    }

    @Override
    public void save(Resume resume) {
        // get searchKey and check for exist
        Object searchKey = getNotExistKey(resume.getUuid());
        //save
        doSave(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        // get searchKey and check for not exist
        Object searchKey = getExistKey(uuid);
        //get
        return doGet(searchKey);
    }

    @Override
    public void delete(String uuid) {
        // get searchKey and check for not exist
        Object searchKey = getExistKey(uuid);
        // delete
        doDelete(searchKey);

    }

    protected Object getNotExistKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if(checkExist(searchKey)){
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected Object getExistKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if(!checkExist(searchKey)){
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

}
