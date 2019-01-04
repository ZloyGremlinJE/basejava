package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.prepareRequest("DELETE FROM resume");

/*
try (Connection conn = sqlHelper.getConnection();
PreparedStatement ps = conn.prepareStatement("DELETE FROM resume")) {
ps.execute();
} catch (SQLException e) {
throw new StorageException(e);
}
*/
    }

    @Override
    public Resume get(String uuid) {
        try (Connection conn = sqlHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r WHERE r.uuid =?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void update(Resume r) {
        try (Connection conn = sqlHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
            ps.setString(2, r.getUuid());
            ps.setString(1, r.getFullName());
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void save(Resume r) {
        try (Connection conn = sqlHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
        } catch (SQLException e) {
            throw new ExistStorageException(r.getUuid());
        }

    }

    @Override
    public void delete(String uuid) {
        try (Connection conn = sqlHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM resume WHERE uuid = ?")) {
            ps.setString(1, uuid);
           if(ps.executeUpdate()==0){
               throw new NotExistStorageException(uuid);
           }
        } catch (SQLException e) {

        }
    }

    @Override
    public List<Resume> getAllSorted() {
        try (Connection conn = sqlHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT *  FROM resume ORDER BY full_name, uuid")) {
            ResultSet rs = ps.executeQuery();
            List<Resume> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return result;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public int size() {
        try (Connection conn = sqlHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(uuid)  FROM resume")) {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new StorageException("Resume count error");
            }
            return rs.getInt("count");
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}