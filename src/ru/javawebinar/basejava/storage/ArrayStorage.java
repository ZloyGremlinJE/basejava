package ru.javawebinar.basejava.storage;
import ru.javawebinar.basejava.model.Resume;
/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        if (checkSave(r) < 0) {
            storage[size] = r;
            size++;
        }
    }

    public void delete(String uuid) {
        int index = checkDelete(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
