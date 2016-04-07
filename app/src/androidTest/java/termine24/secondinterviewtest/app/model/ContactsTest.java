package termine24.secondinterviewtest.app.model;

import android.os.Parcel;

/**
 * Created by shanemurphy on 14/01/2016.
 * Copyright (c) 2016 Shore. All rights reserved.
 */
public class ContactsTest extends ParcelableTest {

    private Contacts contacts;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        String name = "testguy";
        String email = "testguy@shore.com";
        String phoneNumber = "01234567890";
        Contact contact = new Contact(name, email, phoneNumber);
        contacts = new Contacts();
        contacts.add(contact);
    }

    public void testEmptyConstructor() throws Exception {
        Contacts emptyContact = new Contacts();
        assertNotNull("Empty constructor returning null", emptyContact);
        assertEquals("Size is not 0", emptyContact.size(), 0);
    }

    public void testConstructor() throws Exception {
        assertNotNull("Parameter constructor is returning null", contacts);
        assertEquals("Size is not 1", contacts.size(), 1);
    }

    public void testParcelable() throws Exception {
        Parcel p = createParcel(contacts);
        Contacts c = Contacts.CREATOR.createFromParcel(p);
        assertNotNull("Empty constructor returning null", c);
        assertEquals("Contact size is not the same", c.size(), 1);
    }
}
