package ru.javawebinar.basejava.storage;

import org.junit.Ignore;
import org.junit.Test;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    /**
     * This test is not applicable for HashMap. It has no size limitation.
     */
    @Override
    @Ignore
    @Test
    public void saveOverflow() {
        super.saveOverflow();
    }
}