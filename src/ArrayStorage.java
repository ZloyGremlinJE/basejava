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
        storage[size()] = r;
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
        int size = size();
        for (int i = 0; i < size; i++) {
            /*Находим индекс удаляемого элемента и удаляем путем перезаписи на предыдущий элемент
             всех не null элементов массива, идущих после удаляемого.*/
            if (storage[i].uuid.equals(uuid)) {
                int removeIndex = i;
                System.arraycopy(storage, removeIndex + 1, storage, removeIndex, size - removeIndex);
                break;
            }
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
