package ru.javawebinar.phonebook.storage;

import ru.javawebinar.phonebook.exception.NotExistStorageException;
import ru.javawebinar.phonebook.model.Contact;
import ru.javawebinar.phonebook.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.doRequest(PreparedStatement::execute, "DELETE FROM phone_book.public.phone_book_root");

    }

    @Override
    public void update(Contact contact) {
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("UPDATE phone_book.public.phone_book_root SET (full_name,phone_number,department) =(?,?,?) WHERE uuid = ?")) {
//            ps.setString(1, contact.getFullName());
//            ps.setString(2, contact.getPhoneNumbers("|"));
//            ps.setString(3, contact.getDepartment());
//            ps.setString(4, contact.getUuid());
//            ps.execute();
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
//
    }

    @Override
    public void save(Contact contact) {
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("INSERT INTO phone_book.public.phone_book_root (uuid, full_name, phone_number,department) VALUES (?,?,?,?)")) {
//            ps.setString(1, contact.getUuid());
//            ps.setString(2, contact.getFullName());
//            ps.setString(3, contact.getPhoneNumbers("|"));
//            ps.setString(4, contact.getDepartment());
//            ps.execute();
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
//
//
   }

    @Override
    public Contact get(String uuid) {
        return sqlHelper.doRequest(ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Contact contact = new Contact(uuid, rs.getString("full_name"));
            return contact;
        }, "SELECT * FROM phone_book.public.phone_book_root r WHERE r.uuid =?");
   }

    @Override
    public void delete(String uuid) {
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("DELETE FROM phone_book.public.phone_book_root  WHERE uuid = ?")) {
//            ps.setString(1, uuid);
//            if (ps.executeUpdate() == 0) {
//                throw new NotExistStorageException(uuid);
//            }
//
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
//
    }

    @Override
   public List<Contact> getAllSorted() {
//        try (Connection conn = connectionFactory.getConnection();
//        PreparedStatement ps = conn.prepareStatement("SELECT *  FROM phone_book.public.phone_book_root")) {
//            ResultSet rs = ps.executeQuery();
//            List<Contact> contacts = new ArrayList<>();
//            String currentUUID = "";
//            Contact currentContact = null;
//            while(rs.next()){
//                currentUUID = rs.getString("uuid");
//                currentContact = new Contact(currentUUID,rs.getString("full_name"));
//                currentContact.addPhoneNumbers(rs.getString("phone_number"),"|");
//                currentContact.setDepartment(rs.getString("department"));
//                contacts.add(currentContact);
//            }
//            return contacts;
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
   return null; }

    @Override
    public int size() {
//        try (Connection conn = connectionFactory.getConnection();
//             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(uuid)  FROM phone_book.public.phone_book_root")) {
//            ResultSet rs = ps.executeQuery();
//            return rs.next() ? rs.getInt(1) : 0;
//        } catch (SQLException e) {
//            throw new StorageException(e);
//        }
   return 0; }
}
