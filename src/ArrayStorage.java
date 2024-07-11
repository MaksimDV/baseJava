/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                size--;
                storage[i] = storage[size];
                storage[size] = null;

            }
        }
    }

        /**
         * @return array, contains only Resumes in storage (without null)
         */
        Resume[] getAll () {
            Resume[] filteredStorage = new Resume[size];
            for (int i = 0; i < size; i++) {
                filteredStorage[i] = storage[i];
            }

            return filteredStorage;
        }

        int size() {
            return size;
        }
}
