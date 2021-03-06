package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serializer.StrategySerialize;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private File directory;
    private StrategySerialize strategySerialize;

    protected FileStorage(File directory, StrategySerialize strategySerialize) {

        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.strategySerialize = strategySerialize;
    }

    //protected

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            strategySerialize.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Error writing file", resume.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Error creating file " + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(resume, file);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return strategySerialize.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Error reading file", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("Error deleting file", file.getName());
        }
    }

    @Override
    protected List<Resume> getCopyList() {
        File[] fileList = checkingListFiles();
        List<Resume> copyList = new ArrayList<>(size());
        for (File file : fileList) {
            copyList.add(doGet(file));
        }
        return copyList;
    }

    @Override
    public void clear() {
        File[] fileList = checkingListFiles();
        for (File file : fileList) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        File[] fileList = checkingListFiles();
        return fileList.length;
    }

    private File[] checkingListFiles() {
        File[] fileList = directory.listFiles();
        if (fileList == null) {
            throw new StorageException("Error getting list of files in directory");
        }
        return fileList;
    }

}
