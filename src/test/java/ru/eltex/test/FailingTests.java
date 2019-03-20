package ru.eltex.test;

import org.junit.Assert;
import org.junit.Test;
import ru.eltex.phonebook.User;

public class FailingTests {
    @Test
    public void fail1() {
        Assert.fail();
    }

    @Test
    public void fail2() {
        User user = new User(0, "Sam", "891398213");
        Assert.assertEquals("laksjdfkladf", user.getName());
    }
}
