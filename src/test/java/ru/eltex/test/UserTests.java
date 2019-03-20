package ru.eltex.test;

import org.junit.Assert;
import org.junit.Test;
import ru.eltex.phonebook.User;

public class UserTests {
    @Test
    public void ctorSetsFields() {
        int expectedId = 10;
        String expectedName = "Max";
        String expectedPhoneNumber = "8888811111";

        User user = new User(expectedId, expectedName, expectedPhoneNumber);

        Assert.assertEquals(expectedId, user.getId());
        Assert.assertEquals(expectedName, user.getName());
        Assert.assertEquals(expectedPhoneNumber, user.getPhoneNumber());
    }

    @Test
    public void ctorThrowsExceptionOnIllegalArgs() {
        try {
            new User(0, null, "888");
            Assert.fail("Setting null name didn't throw an exception");
        } catch (IllegalArgumentException e) { }

        try {
            new User(0, "Bob", null);
            Assert.fail("Setting null phoneNumber didn't throw an exception");
        } catch (IllegalArgumentException e) { }

        try {
            new User(0, "", "88");
            new User(0, "    ", "88");
            new User(0, "\t", "88");
            Assert.fail("Setting blank name didn't throw an exception");
        } catch (IllegalArgumentException e) { }

        try {
            new User(0, "Bob", "");
            new User(0, "Bob", "    ");
            new User(0, "Bob", "\t");
            Assert.fail("Setting blank phoneNumber didn't throw an exception");
        } catch (IllegalArgumentException e) { }
    }
}
