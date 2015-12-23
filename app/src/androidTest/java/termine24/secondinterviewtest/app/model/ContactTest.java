package termine24.secondinterviewtest.app.model;

import android.os.Parcel;

import junit.framework.TestCase;

/**
 * Created by shanemurphy on 23/12/2015.
 * Copyright (c) 2015 Shore. All rights reserved.
 */
public class ContactTest extends ParcelableTest {

    private String name;
    private String email;
    private String phoneNumber;
    private Contact contact;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        name = "testguy";
        email = "testguy@shore.com";
        phoneNumber = "01234567890";
        contact = new Contact(name, email, phoneNumber);
    }

    public void testEmptyConstructor() throws Exception {
        Contact emptyContact = new Contact();
        assertNotNull("Empty constructor returning null", emptyContact);
    }

    public void testConstructor() throws Exception {
        assertNotNull("Parameter constructor is returning null", contact);
        assertEquals("Name is not the same as the constructor received", name, contact.getName());
        assertEquals("Email is not the same as the constructor received", email, contact.getEmailAddress());
        assertEquals("PhoneNumber is not the same as the constructor received", phoneNumber, contact.getPhoneNumber());
    }

    public void testParcelable() throws Exception {
        Parcel p = createParcel(contact);
        Contact c = (Contact) Contact.CREATOR.createFromParcel(p);
        assertEquals("Name is not the same as the parcelable constructor received", name, c.getName());
        assertEquals("Email is not the same as the parcelable constructor received", email, c.getEmailAddress());
        assertEquals("PhoneNumber is not the same as the parcelable constructor received", phoneNumber, c.getPhoneNumber());
    }


    public void testSettersAndGetters() throws Exception {
        String name = "test2";
        contact.setName(name);
        String email = "test2@shore.com";
        contact.setEmailAddress(email);
        String phonenumber = "9876543210";
        contact.setPhoneNumber(phonenumber);
        assertEquals("Get/Set name doesn't work", name, contact.getName());
        assertEquals("Get/Set email doesn't work", email, contact.getEmailAddress());
        assertEquals("Get/Set phonenumber doesn't work", phonenumber, contact.getPhoneNumber());

    }


}
