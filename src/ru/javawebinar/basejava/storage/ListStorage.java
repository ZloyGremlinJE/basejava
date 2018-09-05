package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> resumeArrayList = new ArrayList<>();

    @Override
    public void clear() {
        resumeArrayList.clear();
    }

    @Override
    public Resume[] getAll() {
        return resumeArrayList.toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return resumeArrayList.size();
    }

    @Override
    protected void doUpdate(Object searchIndex, Resume resume) {
        resumeArrayList.set((Integer) searchIndex, resume);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (uuid.equals(resumeArrayList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Object searchIndex, Resume resume) {
        resumeArrayList.add(resume);
    }

    @Override
    protected Resume doGet(Object searchIndex) {
        return resumeArrayList.get((Integer) searchIndex);
    }

    @Override
    protected void doDelete(Object searchIndex) {
        resumeArrayList.remove(((Integer) searchIndex).intValue());
    }

    @Override
    protected boolean checkExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }
}
