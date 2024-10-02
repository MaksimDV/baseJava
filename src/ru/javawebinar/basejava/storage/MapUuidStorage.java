package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {

    private final Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected Object getKey(String uuid) {
        return uuid;
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
        return resumeMap.containsKey((String) key);
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    protected List<Resume> getList() {
        return new ArrayList<>(resumeMap.values());
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
