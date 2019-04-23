package ru.javawebinar.phonebook.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.phonebook.Config;
import ru.javawebinar.phonebook.exception.NotExistStorageException;
import ru.javawebinar.phonebook.model.Contact;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final Contact CONTACT_2;
    private static final Contact CONTACT_3;
    private static final Contact CONTACT_4;

    static {

        CONTACT_2 = new Contact(UUID.randomUUID().toString(), "FullName2");
        CONTACT_3 = new Contact(UUID.randomUUID().toString(), "FullName3");
        CONTACT_4 = new Contact(UUID.randomUUID().toString(), "FullName4");
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        CONTACT_2.addPhoneNumber("2-24-80");
        CONTACT_2.addPhoneNumber("3-15-46");
        CONTACT_3.addPhoneNumber("4-15-46");
        CONTACT_3.addPhoneNumber("4-15-46");
        CONTACT_4.addPhoneNumber("5-15-46");
        CONTACT_4.addPhoneNumber("5-15-46");

        storage.save(CONTACT_2);
        storage.save(CONTACT_3);
        storage.save(CONTACT_4);

    }

    @Test
    public void clear() throws Exception {
        storage.clear();
    }

    @Test
    public void get() throws Exception {
        assertGet(CONTACT_2);
        assertGet(CONTACT_3);
        assertGet(CONTACT_4);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Contact> testableArray = storage.getAllSorted();
        assertEquals(testableArray, Arrays.asList(CONTACT_2, CONTACT_3, CONTACT_4));
        for (Contact s : testableArray) {
            System.out.println(s.getPhoneNumbers());

        }
    }

    @Test
    public void save() throws Exception {
        storage.clear();
        storage.save(CONTACT_2);
        storage.save(CONTACT_3);
        storage.save(CONTACT_4);
        assertGet(CONTACT_2);
        assertGet(CONTACT_3);
        assertGet(CONTACT_4);
    }

    @Test
    public void update() throws Exception {
        CONTACT_2.setDepartment("OIT");
        storage.update(CONTACT_2);

        assertTrue(CONTACT_2.equals(storage.get(CONTACT_2.getUuid())));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(CONTACT_2.getUuid());
        assertSize(2);
        storage.get(CONTACT_2.getUuid());
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    private void assertGet(Contact contact) {
        assertEquals(contact, storage.get(contact.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}
