package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> resumeList = new ArrayList<>();


    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void updateResume(Resume resume, Object key) {
        resumeList.set((int) key, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object key) {
        resumeList.add(resume);
    }

    @Override
    protected Resume getResume(Object key) {
        return resumeList.get((int) key);
    }

    @Override
    protected void deleteResume(Object key) {
        resumeList.remove((int) key);
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public Resume[] getAll() {
        return resumeList.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return resumeList.size();
    }
}
