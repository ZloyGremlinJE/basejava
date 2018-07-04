import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, size() - 1, null);// обнуляем только ячейки в ктр. не null
    }

    void save(Resume r) {
        int size = size();
        storage[size] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        //найдем индекс удаляемого элемента
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            Resume[] copy = getAll();
            System.arraycopy(copy, index , storage, index, size() );
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                size++;
            } else break;

        }
        return size;
    }
}
