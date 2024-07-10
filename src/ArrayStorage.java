/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int arraySize = 0;

    void clear() {
        for (int i = 0; i < arraySize; i++) {
            storage[i] = null;
        }
        arraySize = 0;
    }

    void save(Resume r) {
        storage[arraySize] = r;
        arraySize++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < arraySize; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < arraySize; i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = storage[arraySize - 1];
                storage[arraySize - 1] = null;
                arraySize--;
            }
        }
    }

        /**
         * @return array, contains only Resumes in storage (without null)
         */
        Resume[] getAll () {
            Resume[] filteredStorage = new Resume[arraySize];
            for (int i = 0; i < arraySize; i++) {
                filteredStorage[i] = storage[i];
            }

            return filteredStorage;
        }

        int size() {
            return arraySize;
        }
}
