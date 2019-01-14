package ru.javawebinar.basejava.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
           throw ExceptionUtil.convertExeption(e);
        }
    }
}
