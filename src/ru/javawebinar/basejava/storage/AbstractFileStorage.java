package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected abstract void writeResume(Resume r, OutputStream os) throws IOException;

    protected abstract Resume readResume(InputStream is) throws IOException;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "Directory must not be NULL");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }

        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writeable");
        }
        this.directory = directory;
    }

    @Override
    protected File getKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void updateResume(Resume resume, File file) {
        try {
            writeResume(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, File file) {
        try {
            if (!file.createNewFile()) {
                throw new StorageException("File already exist", file.getName());
            }
            updateResume(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return readResume(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> getList() {
        List<Resume> resumes = new ArrayList<>();
        for (File file : getDirectoryFiles(directory)) {
            resumes.add(getResume(file));
        }
        return resumes;
    }

    @Override
    public void clear() {
        for (File file : getDirectoryFiles(directory)) {
            deleteResume(file);
        }
    }

    @Override
    public int size() {
        return getDirectoryFiles(directory).length;
    }

    private File[] getDirectoryFiles(File directory) {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory read error", directory.getName());
        }
        return files;
    }
}
