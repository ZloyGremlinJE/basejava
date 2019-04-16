package ru.javawebinar.phonebook.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.phonebook.Config;
import ru.javawebinar.phonebook.model.Contact;

import java.io.File;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

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
    public void save() throws Exception {
        storage.clear();
        storage.save(CONTACT_2);
        storage.save(CONTACT_3);
        storage.save(CONTACT_4);
        assertGet(CONTACT_2);
        assertGet(CONTACT_3);
        assertGet(CONTACT_4);
    }



    private void assertGet(Contact contact) {
        assertEquals(contact, storage.get(contact.getUuid()));
    }
}
