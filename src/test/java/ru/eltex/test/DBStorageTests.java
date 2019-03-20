package ru.eltex.test;

import org.junit.*;
import ru.eltex.phonebook.DBStorage;
import ru.eltex.phonebook.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBStorageTests {
    private static DBStorage db;
    private List<User> insertedUsers;

    @BeforeClass
    public static void initDB() {
        db = new DBStorage("users_test");
    }

    @Before
    public void initInsertedUsers() {
        insertedUsers = new ArrayList<>();
    }

    @Test
    public void testUserInsertion() throws SQLException {
        insertedUsers.add(db.insertNewUser("Owl", "9853201"));
        insertedUsers.add(db.insertNewUser("Kolya", "90123490"));
        insertedUsers.add(db.insertNewUser("SuperUser", "534689231"));

        List<User> dbUsers = db.getAllUsers();

        Assert.assertArrayEquals(insertedUsers.toArray(), dbUsers.toArray());
    }

    @Test
    public void testUserRemoving() throws SQLException {
        insertedUsers.add(db.insertNewUser("Owl", "9853201"));
        insertedUsers.add(db.insertNewUser("Kolya", "90123490"));

        User userToBeRemoved = db.insertNewUser("SuperUser", "534689231");
        db.removeUserById(userToBeRemoved.getId());
        List<User> usersAfterRemoving = db.getAllUsers();

        Assert.assertArrayEquals(insertedUsers.toArray(), usersAfterRemoving.toArray());
    }

    @After
    public void clear() {
        for(User insertedUser : insertedUsers) {
            try {
                db.removeUserById(insertedUser.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        insertedUsers.clear();
    }
}
