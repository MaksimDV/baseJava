package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> RESUME_LIST = new ArrayList<>();


    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < RESUME_LIST.size(); i++) {
            if (RESUME_LIST.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        RESUME_LIST.set((int) key, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        RESUME_LIST.add(resume);
    }

    @Override
    protected Resume getResume(Object key) {
        return RESUME_LIST.get((int) key);
    }

    @Override
    protected void deleteResume(Object key) {
        RESUME_LIST.remove((int) key);
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    public void clear() {
        RESUME_LIST.clear();
    }

    @Override
    public Resume[] getAll() {
        return RESUME_LIST.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return RESUME_LIST.size();
    }
}
