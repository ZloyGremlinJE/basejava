package ru.javawebinar.phonebook.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface prepareRequest<Element> {
    Element prepareRequest(PreparedStatement ps) throws SQLException;
}
