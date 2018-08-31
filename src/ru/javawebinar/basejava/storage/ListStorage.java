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
    protected void doUpdate(int searchIndex, Resume resume) {
        resumeArrayList.set(searchIndex, resume);
    }

    @Override
    protected int getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (uuid.equals(resumeArrayList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(int searchIndex, Resume resume) {
        resumeArrayList.add(resume);
    }

    @Override
    protected Resume doGet(int searchIndex, String uuid) {
        return resumeArrayList.get(searchIndex);
    }

    @Override
    protected void doDelete(int searchIndex, String uuid) {
        resumeArrayList.remove(searchIndex);
    }
}
