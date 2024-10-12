package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ObjectStreamStorage extends AbstractFileStorage {
    SerializationStrategy serializationStrategy;

    public ObjectStreamStorage(File directory, SerializationStrategy serializationStrategy) {
        super(directory);
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
