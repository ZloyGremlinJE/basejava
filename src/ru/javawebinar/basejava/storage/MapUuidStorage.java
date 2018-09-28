package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    protected Map<String, Resume> resumeHashMap = new HashMap<>();

    @Override
    public void clear() {
        resumeHashMap.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> copy = new ArrayList<>(resumeHashMap.values());
        return copy;
    }

    @Override
    public int size() {
        return resumeHashMap.size();
    }


    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
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
    protected void doSave(Resume resume, Object searchKey) {
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
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
