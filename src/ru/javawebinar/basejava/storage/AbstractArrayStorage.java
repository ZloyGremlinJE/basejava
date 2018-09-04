package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected void doUpdate(Object searchIndex, Resume resume) {
        storage[(Integer) searchIndex] = resume;
    }

    @Override
    protected Resume doGet(Object searchIndex) {
        return storage[(Integer) searchIndex];
    }

    @Override
    protected void doDelete(Object searchIndex) {
        fillDeletedElement((Integer) searchIndex);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void doSave(Object searchIndex, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume, (Integer) searchIndex);
            size++;
        }
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume resume, int index);


}