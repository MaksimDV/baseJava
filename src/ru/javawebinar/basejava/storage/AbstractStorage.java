package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    protected abstract SK getKey(String uuid);

    protected abstract void updateResume(Resume resume, SK key);

    protected abstract void saveResume(Resume resume, SK key);

    protected abstract Resume getResume(SK key);

    protected abstract void deleteResume(SK key);

    protected abstract boolean isExist(SK key);

    protected abstract List<Resume> getList();

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    @Override
    public Resume get(String uuid) {
        LOG.info("Get" + uuid);
        SK key = getKeyIfNotExistException(uuid);
        return getResume(key);
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update" + resume);
        SK key = getKeyIfNotExistException(resume.getUuid());
        updateResume(resume, key);
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save" + resume);
        SK key = getKeyIfExistException(resume.getUuid());
        saveResume(resume, key);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete" + uuid);
        SK key = getKeyIfNotExistException(uuid);
        deleteResume(key);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> resumeList = getList();
        return resumeList.stream()
                .sorted(RESUME_COMPARATOR)
                .toList();
    }

    private SK getKeyIfNotExistException(String uuid) {
        SK key = getKey(uuid);
        if(!isExist(key)) {
            LOG.warning("Resume " + uuid + " not exist in the storage");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private SK getKeyIfExistException(String uuid) {
        SK key = getKey(uuid);
        if(isExist(key)) {
            LOG.warning("Resume " + uuid + " already exists in the storage");
            throw new ExistStorageException(uuid);
        }
        return key;
    }
}
