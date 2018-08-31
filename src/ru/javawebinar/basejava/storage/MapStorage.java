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
    protected void doUpdate(int searchIndex, Resume r) {
        resumeHashMap.put(r.getUuid(), r);
    }

    @Override
    protected int getSearchKey(String uuid) {
        if (resumeHashMap.containsKey(uuid)) {
            return 0;
        }
        return -1;
    }

    @Override
    protected void doSave(int searchIndex, Resume r) {
        resumeHashMap.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(int searchIndex, String uuid) {
        return resumeHashMap.get(uuid);
    }

    @Override
    protected void doDelete(int searchIndex, String uuid) {
        resumeHashMap.remove(uuid);
    }
}
