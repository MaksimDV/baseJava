package ru.javawebinar.basejava;

import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    protected static final File PROPS = new File("/Users/maksimd/dev/basejava/config/resumes.properties");
    private static final Config INSTANCE = new Config();
    private final File STORAGE_DIR;
    private final Storage storage;

    public static Config get() {
        return INSTANCE;
    }

    public File getStorageDir() {
        return STORAGE_DIR;
    }

    public Storage getStorage() {
        return storage;
    }

    private Config() {
        Properties props = new Properties();
        try (InputStream is = new FileInputStream(PROPS)) {
            props.load(is);
            STORAGE_DIR = new File(props.getProperty("storage.dir"));
            storage = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));

        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file");
        }
    }
}
