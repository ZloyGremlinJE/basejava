package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> resumeArrayList = new ArrayList<>();

    @Override
    public void clear() {
        resumeArrayList.clear();
    }

    @Override //ListStorage
    public void update(Resume r) {
        int index = resumeArrayList.indexOf(r);
        if (index == -1) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            resumeArrayList.set(index, r);
        }
    }

    @Override
    public void save(Resume r) {
        if (resumeArrayList.contains(r)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            resumeArrayList.add(r);
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return resumeArrayList.get(index);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        resumeArrayList.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return resumeArrayList.toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return resumeArrayList.size();
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (uuid.equals(resumeArrayList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

}
