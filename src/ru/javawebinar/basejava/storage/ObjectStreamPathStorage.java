package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    SerializationStrategy serializationStrategy;

    public ObjectStreamPathStorage(String path, SerializationStrategy serializationStrategy) {
        super(path);
        this.serializationStrategy = serializationStrategy;
    }

    @Override
    protected void writeResume(Resume r, OutputStream os) throws IOException {
        serializationStrategy.write(r, os);
    }

    @Override
    protected Resume readResume(InputStream is) throws IOException {
        return serializationStrategy.read(is);
    }
}
