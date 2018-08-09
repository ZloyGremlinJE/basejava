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

    protected void insertElement(Resume r, int index) {
        int srcPos = -index - 1;
        System.arraycopy(storage, srcPos, storage, srcPos + 1, size - srcPos);
        storage[srcPos] = r;

    }

    protected void fillDeletedElement(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
    }
}
