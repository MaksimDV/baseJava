package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;
    private final String EXIST_ERROR = " already exists in the storage";
    private final String NOT_EXIST_ERROR = " does not exist in the storage";
    private final String STORAGE_ERROR = "The storage is fully";

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void update(Resume resume) {
        if (getIndex(resume.getUuid()) >= 0) {

        } else {
            System.out.println(resume.getUuid() + NOT_EXIST_ERROR);
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {

        if (storage.length == size) {
            System.out.println(STORAGE_ERROR);
        } else if (getIndex(r.getUuid()) >= 0) {
            System.out.println(r.getUuid() + EXIST_ERROR);
        } else {
            storage[size++] = r;
        }
    }

    public Resume get(String uuid) {
        int index = this.getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        } else {
            System.out.println(uuid + NOT_EXIST_ERROR);
            return null;
        }
    }

    public void delete(String uuid) {
        int index = this.getIndex(uuid);
        if (index >= 0) {
            size--;
            storage[index] = storage[size];
            storage[size] = null;
        } else {
            System.out.println(uuid + NOT_EXIST_ERROR);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allResume = Arrays.copyOf(storage, size);
        return allResume;
    }

    public int size() {
        return size;
    }
}
