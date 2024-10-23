package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializationStrategy.XmlStreamSerializationStrategy;

public class XmlStreamPathStorageTest extends AbstractStorageTest {
    public XmlStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new XmlStreamSerializationStrategy()));
    }
}
