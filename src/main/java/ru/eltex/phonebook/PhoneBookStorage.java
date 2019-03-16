package ru.eltex.phonebook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public abstract class PhoneBookStorage {
    protected final PhoneBook phoneBook;

    public PhoneBookStorage(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    public abstract List<User> getAllUsers() throws Exception;
    public abstract User insertNewUser(String name, String phoneNumber) throws Exception;
    public abstract boolean removeUserById(int id) throws Exception;
}
