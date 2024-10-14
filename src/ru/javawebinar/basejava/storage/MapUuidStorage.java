package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {

    private final Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected void updateResume(Resume resume, String key) {
        resumeMap.put(key, resume);
    }

    @Override
    protected void saveResume(Resume resume, String key) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(String key) {
        return resumeMap.get(key);
    }

    @Override
    protected void deleteResume(String key) {
        resumeMap.remove(key);
    }

    @Override
    protected boolean isExist(String key) {
        return resumeMap.containsKey(key);
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
