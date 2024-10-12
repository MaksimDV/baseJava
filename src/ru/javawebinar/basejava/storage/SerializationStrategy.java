package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SerializationStrategy {
    void write(Resume r, OutputStream os) throws IOException;

    Resume read(InputStream is) throws IOException;
}
