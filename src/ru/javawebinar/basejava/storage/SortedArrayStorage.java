package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
/**
 * Storage of resume based on sorted array
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    protected void entryInArray(Resume r, int index) {
        int srcPos = -index - 1;
        System.arraycopy(storage, srcPos, storage, -index, size + index + 1);
        storage[srcPos] = r;

    }

    protected void deleteFromArray(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index);
    }
}
