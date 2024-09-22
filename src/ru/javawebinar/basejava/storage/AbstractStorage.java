package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public Resume get(String uuid) {
        Object key = getKeyIfNotExistException(uuid);
        return getResume(key);
    }

    @Override
    public void update(Resume resume) {
        Object key = getKeyIfNotExistException(resume.getUuid());
        updateResume(resume, key);
    }

    @Override
    public void save(Resume resume) {
        Object key = getKeyIfExistException(resume.getUuid());
        saveResume(resume, key);
    }

    @Override
    public void delete(String uuid) {
        Object key = getKeyIfNotExistException(uuid);
        deleteResume(key);
    }


    private Object getKeyIfNotExistException(String uuid) {
        Object key = getKey(uuid);
        if(!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private Object getKeyIfExistException(String uuid) {
        Object key = getKey(uuid);
        if(isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    protected abstract Object getKey(String uuid);

    protected abstract void updateResume(Resume resume, Object key);

    protected abstract void saveResume(Resume resume, Object key);

    protected abstract Resume getResume(Object key);

    protected abstract void deleteResume(Object key);

    protected abstract boolean isExist(Object key);
}
