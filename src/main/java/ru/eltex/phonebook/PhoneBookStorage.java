package ru.eltex.phonebook;

import java.util.List;

public interface PhoneBookStorage {
    List<User> getAllUsers() throws Exception;
    User insertNewUser(String name, String phoneNumber) throws Exception;
    boolean removeUserById(int id) throws Exception;
}
