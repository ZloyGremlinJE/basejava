package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return null;
    }

    @Override
    protected void doUpdate(Resume resume, Path searchKey) {

    }

    @Override
    protected boolean isExist(Path searchKey) {
        return false;
    }

    @Override
    protected void doSave(Resume resume, Path searchKey) {

    }

    @Override
    protected Resume doGet(Path searchKey) {
        return null;
    }

    @Override
    protected void doDelete(Path searchKey) {

    }

    @Override
    protected List<Resume> getCopyList() {
        return null;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {

        return 0;
    }



    protected abstract void doWrite(Resume resume, OutputStream outputStream) throws IOException;
    protected abstract Resume doRead(InputStream is) throws IOException;
}
