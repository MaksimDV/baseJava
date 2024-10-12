package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected abstract void writeResume(Resume r, OutputStream os) throws IOException;

    protected abstract Resume readResume(InputStream is) throws IOException;

    public AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "Directory must not be NULL");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or  is not writable");
        }
    }

    @Override
    protected Path getKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void updateResume(Resume resume, Path path) {
        try {
            writeResume(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, Path path) {
        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
            updateResume(resume, path);
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return readResume(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileSystem().toString(), e);
        }
    }

    @Override
    protected void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("File delete error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected List<Resume> getList() {
        List<Resume> resumes = new ArrayList<>();
        try (Stream<Path> paths = Files.list(directory)) {
            paths.forEach(path -> resumes.add(getResume(path)));
        } catch (IOException e) {
            throw new StorageException("Directory read error", directory.getFileName().toString(), e);
        }
        return resumes;
    }

    @Override
    public void clear() {
        try (Stream<Path> paths = Files.list(directory)) {
            paths.forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("Path delete error", directory.getFileSystem().toString(), e);
        }

    }

    @Override
    public int size() {
        try (Stream<Path> paths = Files.list(directory)) {
            return (int) paths.count();
        } catch (IOException e) {
            throw new StorageException("Directory size error", directory.getFileName().toString(), e);
        }

    }
}
