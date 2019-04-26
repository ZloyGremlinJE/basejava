package ru.javawebinar.phonebook.sql;

import org.postgresql.util.PSQLException;
import ru.javawebinar.phonebook.exception.ExistStorageException;
import ru.javawebinar.phonebook.exception.StorageException;

import java.sql.SQLException;

public class ExeptionUtil {

    public ExeptionUtil() {
    }

    public static StorageException convertExeption(SQLException e) {
        if (e instanceof PSQLException) {
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}
