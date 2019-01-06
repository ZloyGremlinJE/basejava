package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public interface prepareRequest<Element> {
        Element prepareRequest(PreparedStatement ps) throws SQLException;
    }

    public <Element> Element doRequest(prepareRequest<Element> prepareRequest, String requestText) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(requestText)) {
            return prepareRequest.prepareRequest(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new ExistStorageException("resume already exist");
            } else {
                throw new StorageException(e);
            }
        }
    }
}
