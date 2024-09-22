package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    @Override
    protected Resume getResume(Object key) {
        return storage[(int) key];
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        storage[(int) key] = resume;
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        if (STORAGE_LIMIT == size) {
            throw new StorageException("The storage is fully", resume.getUuid());
        } else {
            addElement(resume, (int) key);
            size++;
        }
    }

    @Override
    protected void deleteResume(Object key) {
        removeElement((int) key);
        storage[--size] = null;
    }


    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected boolean isExist(Object key) {
        return (int) key >= 0;
    }

    @Override
    protected abstract Integer getKey(String uuid);

    protected abstract void addElement(Resume resume, int index);

    protected abstract void removeElement(int index);
}
