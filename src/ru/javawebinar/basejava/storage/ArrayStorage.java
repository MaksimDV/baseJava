package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void addResume(Resume resume) {
        storage[size++] = resume;
    }

    @Override
    protected void removeResume(String uuid) {
        storage[getIndex(uuid)] = storage[--size];
        storage[size] = null;
    }
}
