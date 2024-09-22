package ru.javawebinar.basejava.storage;

import org.junit.Ignore;
import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest {

    public ListStorageTest() {
        super(new ListStorage());
    }

    /**
     * This test is not applicable for ArrayList. It has no size limitation.
     */
    @Override
    @Ignore
    @Test
    public void saveOverflow() {
        super.saveOverflow();
    }
}