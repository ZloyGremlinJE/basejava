package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.doRequest(PreparedStatement::execute, "DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.doRequest((ps) -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume r = new Resume(uuid, rs.getString("full_name"));
            do {
                writeContact(rs, r);
            } while (rs.next());

            return r;
        }, "" +
                "SELECT * FROM resume r " +
                "LEFT JOIN contact c " +
                "ON r.uuid = c.resume_uuid " +
                " WHERE r.uuid =?");
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                ps.execute();
            }
            // delete contacts
            deleteContactsDB(resume, conn);
            // create contacts
            createContactsDB(resume, conn);
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    createContactsDB(resume, conn);
                    return null;
                }
        );
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.doRequest((ps) -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        }, "DELETE FROM resume WHERE uuid = ?");
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.doRequest((ps) -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            String currentUUID = "";
            Resume currentResume = null;
            while (rs.next()) {
                if (!currentUUID.equals(rs.getString("uuid"))) {
                    currentUUID = rs.getString("uuid");
                    currentResume = new Resume(currentUUID, rs.getString("full_name"));
                    resumes.add(currentResume);
                }
                writeContact(rs, currentResume);
            }
            return resumes;
        }, "SELECT * FROM resume r\n" +
                "LEFT JOIN contact c\n" +
                "ON r.uuid = c.resume_uuid\n" +
                "ORDER BY  full_name, uuid");
    }

    @Override
    public int size() {
        return sqlHelper.doRequest((ps) -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        }, "SELECT COUNT(uuid)  FROM resume");
    }

    private void writeContact(ResultSet rs, Resume currentResume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            ContactType type = ContactType.valueOf(rs.getString("type"));
            currentResume.addContact(type, value);
        }
    }

    private void createContactsDB(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, entry.getKey().name());
                ps.setString(3, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }

    }

    private void deleteContactsDB
            (Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }
}


