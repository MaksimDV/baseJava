package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> resumeList = new ArrayList<>();


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
    protected void updateResume(Resume resume, Integer key) {
        resumeList.set(key, resume);
    }

    @Override
    protected void saveResume(Resume resume, Integer key) {
        resumeList.add(resume);
    }

    @Override
    protected Resume getResume(Integer key) {
        return resumeList.get(key);
    }

    @Override
    protected void deleteResume(Integer key) {
        resumeList.remove(key.intValue());
    }

    @Override
    protected boolean isExist(Integer key) {
        return key != null;
    }

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected List<Resume> getList() {
        return resumeList;
    }
}
