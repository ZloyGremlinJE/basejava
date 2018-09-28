package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// MapStorage with search key not uuid
public class MapStorage extends AbstractStorage {
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
        String uuid = ((Resume) searchKey).getUuid();
        resumeHashMap.put(uuid, resume);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return resumeHashMap.get(uuid);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        resumeHashMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        String uuid = ((Resume) searchKey).getUuid();
        return resumeHashMap.get(uuid);
    }

    @Override
    protected void doDelete(Object searchKey) {
        String uuid = ((Resume) searchKey).getUuid();
        resumeHashMap.remove(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
