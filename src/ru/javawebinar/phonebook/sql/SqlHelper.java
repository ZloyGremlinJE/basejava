package ru.javawebinar.phonebook.sql;

import ru.javawebinar.phonebook.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ru.javawebinar.phonebook.sql.ExeptionUtil.convertExeption;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <Element> Element doRequest(prepareRequest<Element> prepareRequest, String requestText) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(requestText)) {
            return prepareRequest.prepareRequest(ps);
        } catch (SQLException e) {
            throw convertExeption(e);
        }
    }

    public <T> T transactionalExecute(SqlTransaction<T> executor) {
        try (Connection conn = connectionFactory.getConnection()) {
            try {
                conn.setAutoCommit(false);
                T res = executor.execute(conn);
                conn.commit();
                return res;
            } catch (SQLException e) {
                conn.rollback();
                throw ExeptionUtil.convertExeption(e);
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
