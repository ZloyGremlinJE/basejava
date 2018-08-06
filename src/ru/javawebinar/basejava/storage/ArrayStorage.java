package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Storage of resume based on unsorted array
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    protected void entryInArray(Resume r, int index) {
        storage[size] = r;
    }

    protected void deleteFromArray(int index) {
        storage[index] = storage[size];
    }
}
