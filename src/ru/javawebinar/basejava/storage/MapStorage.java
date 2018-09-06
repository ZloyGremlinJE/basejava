package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> resumeHashMap = new HashMap<>();

    @Override
    public void clear() {
        resumeHashMap.clear();
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
    protected void doUpdate(Object searchKey, Resume resume) {
        resumeHashMap.put(resume.getUuid(), resume);
    }

    @Override
    protected String getSearchKey(String uuid) {
        if (resumeHashMap.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }

    @Override
    protected void doSave(Object searchKey, Resume resume) {
        resumeHashMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return resumeHashMap.get(searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        resumeHashMap.remove(searchKey);
    }

    @Override
    protected boolean checkExist(Object searchKey) {
        return searchKey != null;
    }
}
