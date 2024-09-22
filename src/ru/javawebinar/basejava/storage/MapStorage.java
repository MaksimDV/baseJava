package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected Object getKey(String uuid) {
        return resumeMap.containsKey(uuid) ? uuid : null;
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        resumeMap.put((String) key, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object key) {
        return resumeMap.get((String) key);
    }

    @Override
    protected void deleteResume(Object key) {
        resumeMap.remove((String) key);
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public Resume[] getAll() {
        return resumeMap.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
