package termine24.secondinterviewtest.app.model;


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
        assertEquals("Email is not the same as the constructor received", email, contact.getEmail());
        assertEquals("PhoneNumber is not the same as the constructor received", phoneNumber, contact.getMobile());
    }

    //TODO implement test for 100% branch and function coverage


}
