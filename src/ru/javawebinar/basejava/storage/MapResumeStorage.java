package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private final Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected Resume getKey(String uuid) {
        return resumeMap.get(uuid);
    }

    @Override
    protected void updateResume(Resume r, Resume resume) {
        resumeMap.put(r.getUuid(), r);
    }

    @Override
    protected void saveResume(Resume r, Resume resume) {
        resumeMap.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(Resume resume) {
        return resume;
    }

    @Override
    protected void deleteResume(Resume resume) {
        resumeMap.remove((resume).getUuid());
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null && resumeMap.containsKey((resume).getUuid());
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
