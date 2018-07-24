import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[3];
    int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    void save(Resume r) {
        int result = getIndex(r.uuid);
        if (result == -1) {
            storage[size] = r;
            size++;
        } else System.out.println("ERROR : Resume already exists!");
    }

    void update(Resume r) {
        int result = getIndex(r.uuid);
        if (result != -1) {
            System.out.println("Resume update!");
        } else System.out.println("ERROR : Resume not found!");
    }

    Resume get(String uuid) {
        int result = getIndex(uuid);
        if (result != -1) {
            return storage[result];
        } else System.out.println("ERROR : Resume not found!");
        return null;
    }

    void delete(String uuid) {
        int result = getIndex(uuid);
        if (result != -1) {
            size--;
            System.arraycopy(storage, result + 1, storage, result, size - result);
            storage[size] = null;
        } else System.out.println("ERROR : Resume not found!");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
