package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// MapStorage with search key not uuid
public class MapStorage extends AbstractStorage {
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
        String uuid = ((Resume) searchKey).getUuid();
        map.put(uuid, resume);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        String uuid = ((Resume) searchKey).getUuid();
        return map.get(uuid);
    }

    @Override
    protected void doDelete(Object searchKey) {
        String uuid = ((Resume) searchKey).getUuid();
        map.remove(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
