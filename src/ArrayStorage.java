/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].uuid == uuid) {
                storage[i] = null;
            }
        }

        int nonNullIndex = 0;

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                storage[nonNullIndex++] = storage[i];
            }
        }

        storage[nonNullIndex] = null;

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return storage;
    }

    int size() {
        int count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                break;
            }
            count++;
        }
        return count;
    }
}
