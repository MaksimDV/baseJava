package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public abstract class AbstractStorageTest {

    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String FULL_NAME1 = "fullName1";
    private static final Resume RESUME_1 = new Resume(UUID_1, FULL_NAME1);
    private static final String UUID_2 = "uuid2";
    private static final String FULL_NAME2 = "fullName2";
    private static final Resume RESUME_2 = new Resume(UUID_2, FULL_NAME2);
    private static final String UUID_3 = "uuid3";
    private static final String FULL_NAME3 = "fullName3";
    private static final Resume RESUME_3 = new Resume(UUID_3, FULL_NAME3);
    private static final String UUID_4 = "uuid4";
    private static final String FULL_NAME4 = "fullName4";
    private static final Resume RESUME_4 = new Resume(UUID_4, FULL_NAME4);
    private static final String NOT_EXISTED_UUID = "dummy";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        System.out.println();
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void update() {
        storage.update(RESUME_1);
        Assert.assertSame(RESUME_1, storage.get(UUID_1));
    }


    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test (expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Assert.assertEquals(Collections.emptyList(), storage.getAllSorted());
    }

    @Test
    public void getAllSorted() {
        /*
        Add resume2 with the same full name to check sort logic.
        */
        Resume resume2 = new Resume(UUID_4, FULL_NAME2);
        storage.save(resume2);

        List<Resume> actualResumes = storage.getAllSorted();
        List<Resume> expectedResumes = Arrays.asList(RESUME_1, RESUME_2, resume2, RESUME_3);
        Assert.assertEquals(4, actualResumes.size());
        Assert.assertEquals(expectedResumes, actualResumes);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(NOT_EXISTED_UUID);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(NOT_EXISTED_UUID);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
}