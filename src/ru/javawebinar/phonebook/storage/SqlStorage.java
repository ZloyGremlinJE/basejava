package ru.javawebinar.phonebook.storage;

import ru.javawebinar.phonebook.exception.NotExistStorageException;
import ru.javawebinar.phonebook.exception.StorageException;
import ru.javawebinar.phonebook.model.Contact;
import ru.javawebinar.phonebook.sql.ConnectionFactory;

import java.sql.*;
import java.util.List;



public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = ()-> DriverManager.getConnection(dbUrl,dbUser,dbPassword);
    }

    @Override
    public void clear() {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM phone_book.public.phone_book_root")) {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }

    }

    @Override
    public void update(Contact contact) {


    }

    @Override
    public void save(Contact contact) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO phone_book.public.phone_book_root (uuid, full_name, phone_number) VALUES (?,?)")) {
            ps.setString(1, contact.getUuid());
            ps.setString(2, contact.getFullName());
            ps.setString(3, contact.getPhoneNumbers().toString());
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }


    }

    @Override
    public Contact get(String uuid) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM phone_book.public.phone_book_root r WHERE r.uuid =?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Contact(uuid, rs.getString("full_name"));
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public List<Contact> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
