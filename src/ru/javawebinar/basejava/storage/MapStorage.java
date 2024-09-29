package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> RESUME_MAP = new HashMap<>();

    @Override
    protected Object getKey(String uuid) {
        return uuid;
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        RESUME_MAP.put((String) key, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        RESUME_MAP.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object key) {
        return RESUME_MAP.get((String) key);
    }

    @Override
    protected void deleteResume(Object key) {
        RESUME_MAP.remove((String) key);
    }

    @Override
    protected boolean isExist(Object key) {
        return RESUME_MAP.containsKey((String) key);
    }

    @Override
    public void clear() {
        RESUME_MAP.clear();
    }

    @Override
    public Resume[] getAll() {
        return RESUME_MAP.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return RESUME_MAP.size();
    }
}
