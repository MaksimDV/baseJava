package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializationStrategy.JsonStreamSerializationStrategy;

public class JsonStreamPathStorageTest extends AbstractStorageTest {
    public JsonStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new JsonStreamSerializationStrategy()));
    }
}
