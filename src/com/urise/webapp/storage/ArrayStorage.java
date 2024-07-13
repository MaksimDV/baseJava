package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;
    private final String EXIST_ERROR = " already exists in the storage";
    private final String NOT_EXIST_ERROR = " does not exist in the storage";
    private final String STORAGE_ERROR = "The storage is fully";

    private int checkResumeExists(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }

    public void update(Resume resume) {
        if (this.checkResumeExists(resume.getUuid()) >= 0) {
        } else {
            System.out.println(resume.getUuid() + NOT_EXIST_ERROR);
        }
    }

    public void clear() {

        /*
        Applicable method in java.util.Arrays to clear array
        import java.util.Arrays;
        Arrays.fill(storage, null);
        */

        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {

        if (this.checkResumeExists(r.getUuid()) >= 0) {
            System.out.println(r.getUuid() + EXIST_ERROR);
        } else if (storage.length == size) {
            System.out.println(STORAGE_ERROR);
        } else {
            storage[size++] = r;
        }
    }

    public Resume get(String uuid) {
        int i = this.checkResumeExists(uuid);
        if (i >= 0) {
            return storage[i];
        } else {
            System.out.println(uuid + NOT_EXIST_ERROR);
            return null;
        }
    }

    public void delete(String uuid) {
        int i = this.checkResumeExists(uuid);
        if (i >= 0) {
            size--;
            storage[i] = storage[size];
            storage[size] = null;
        } else {
            System.out.println(uuid + NOT_EXIST_ERROR);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {

        /*
        Applicable methods in java.util.Arrays to get array
        import java.util.Arrays;
        Resume[] allResume = Arrays.stream(storage)
                                 .filter(e -> e != null)
                                 .toArray(Resume[]::new);
        */

        Resume[] allResume = new Resume[size];
        for (int i = 0; i < size; i++) {
            allResume[i] = storage[i];
        }
        return allResume;
    }

    public int size() {
        return size;
    }
}
