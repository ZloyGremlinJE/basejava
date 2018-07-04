import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);// заполняем все ячейки значением null
    }

    void save(Resume r) {
        for (int i = 0; i < size(); i++) {
            // ищем первый элемент с null  и записываем в него Resume r
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        Resume result = null;
        for (Resume resume : storage) {
            if (resume == null) break; // массив storage пуст
            if (resume.uuid.equals(uuid)) {
                result = resume;
            }
        }

        return result;
    }

    void delete(String uuid) {
        /* первый цикл находит индекс элемента массива ктр. нужно удалить и обнуляет элемент
           второй цикл  "перемещает" все не null элементы так чтобы они располагались "без дыр".
         */
        int index = 0;
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                index = i;
                break;
            }
        }

        for (int i = index; i < size() - 1; i++) {
            Resume temp = storage[i + 1];
            storage[i] = temp;
            if (storage[i + 1] == null) {
                break;
            } else {
                storage[i + 1] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int index = size();// если заполнен весь массив
        //ищем индекс ячейки массива с первым null
        for (int i = 0; i < size(); i++) {
            if (storage[i] == null) {
                index = i;
                break;
            }
        }
        Resume[] resumesWithoutNull = Arrays.copyOf(storage, index);

        return resumesWithoutNull;
    }

    int size() {
        return storage.length;
    }
}
