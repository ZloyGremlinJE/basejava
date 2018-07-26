import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (storage.length == size) {
            System.out.println("ERROR: The array is full !");
            return;
        }
        if (getIndex(r.uuid) == -1) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("ERROR : Resume already exists!");
        }
    }

    void update(Resume r) {
        int index = getIndex(r.uuid);
        if (index != -1) {
            storage[index] = r;
            System.out.println("Resume update!");
        } else {
            System.out.println("ERROR : Resume not found!");
        }
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("ERROR : Resume not found!");
        }
        return null;
    }

    void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size -1];
            storage[size -1] = null;
            size--;
        } else {
            System.out.println("ERROR : Resume not found!");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size );
    }

    int size() {
        return size;
    }

   private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
