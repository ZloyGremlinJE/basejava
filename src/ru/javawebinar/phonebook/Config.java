package ru.javawebinar.phonebook;

import ru.javawebinar.phonebook.storage.SqlStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    rivate static final File PROPS = new File("config\\phone_book.properties");
    private static final Config INSTANCE = new Config();

    private Properties props = new Properties();
    private File storageDir;
    private SqlStorage sqlStorage;
    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
        sqlStorage = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"),props.getProperty("db.password"));
    }

    public File getStorageDir() {
        return storageDir;
    }

    public SqlStorage getSqlStorage() {
        return sqlStorage;
    }
}
