package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    protected Map<String, Resume> map = new HashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }


    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey(searchKey);
    }
}
