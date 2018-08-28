package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> resumeHashMap = new HashMap<>();

    @Override
    public void clear() {
        resumeHashMap.clear();
    }

   /* @Override // MapStorage
    public void update(Resume r) {
        String uuid = r.getUuid();
        if (!resumeHashMap.containsKey(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        resumeHashMap.put(uuid, r);
    }*/

    @Override
    public void save(Resume r) {
        String uuid = r.getUuid();
        if (resumeHashMap.containsKey(uuid)) {
            throw new ExistStorageException(uuid);
        }
        resumeHashMap.put(uuid, r);

    }

    @Override
    public Resume get(String uuid) {
        if (!resumeHashMap.containsKey(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return resumeHashMap.get(uuid);
    }

    @Override
    public void delete(String uuid) {
        if (!resumeHashMap.containsKey(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        resumeHashMap.remove(uuid);
    }

    @Override
    public Resume[] getAll() {
        return resumeHashMap.values().toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return resumeHashMap.size();
    }

    @Override
    protected boolean checkForExist(Resume r) {
        String uuid = r.getUuid();
       return resumeHashMap.containsKey(uuid);

    }

    @Override
    protected void doUpdate(Resume r) {
        resumeHashMap.put(r.getUuid(), r);
    }
}
