package termine24.secondinterviewtest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import termine24.secondinterviewtest.app.model.Contact;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    private Contact contact;

    @Before
    public void setUp() throws Exception {
        contact = new Contact("New User", "new.user@example.com", "0123456789");
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testUserDetails() throws Exception {

      final String EXPECTED_NAME = "New User";
      final String EXPECTED_EMAIL = "new.user@example.com";
      final String EXPECTED_PHONE = "0123456789";
        Assert.assertEquals(EXPECTED_NAME, contact.getName());
        Assert.assertEquals(EXPECTED_EMAIL, contact.getEmail());
        Assert.assertEquals(EXPECTED_PHONE, contact.getMobile());
    }
}
