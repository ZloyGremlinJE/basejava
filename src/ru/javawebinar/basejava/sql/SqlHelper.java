package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void prepareRequest(String request){
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(request)) {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
    public Connection getConnection(){
        try(Connection conn = connectionFactory.getConnection()) {
            return connectionFactory.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }






}
