import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        for (int i = 0; i < size()-1; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
      Resume  result = null;
        for (Resume resume : storage) {
            if (resume == null) break;
            if (resume.uuid.equals(uuid)) {
                result = resume;
            }
        }

        return result;
    }

    void delete(String uuid) {
        int index = 0;
        for (int i = 0; i < size()-1 ; i++) {
            if(storage[i] == null) break;
            if(storage[i].uuid == uuid){
               index = i;
            }
        }
        for (int i = index; i < size()-2 ; i++) {
            storage[i] = storage[i+1];
            if(storage[i] == null) break;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int index = size()-1;
        for (int i = 0; i < size()-1 ; i++) {
          if(storage[i] == null) {
              index = i;
              break;
          }
        }
        Resume [] resumesWithoutNull = Arrays.copyOfRange(storage, 0, index);

        return resumesWithoutNull;
    }

    int size() {
        return storage.length;
    }
}
