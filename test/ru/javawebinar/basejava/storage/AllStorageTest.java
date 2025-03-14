package ru.javawebinar.basejava.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {ArrayStorageTest.class,
                SortedArrayStorageTest.class,
                ListStorageTest.class,
                MapUuidStorageTest.class,
                MapResumeStorageTest.class,
                ObjectStreamFileStorageTest.class,
                ObjectStreamPathStorageTest.class,
                XmlPathStorageTest.class,
                JsonPathStorageTest.class,
                DataStreamPathStorageTest.class,
                SqlStorageTest.class
        })

public class AllStorageTest {

}