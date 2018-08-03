package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int index = checkSave(r);
        if (index < 0) {
            int srcPos = -index - 1;
            System.arraycopy(storage, srcPos, storage, -index, size + index + 1);
            storage[srcPos] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = checkDelete(uuid);
        if (index >= 0) {
            size--;
            System.arraycopy(storage, index + 1, storage, index, size - index);
            storage[size] = null;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
