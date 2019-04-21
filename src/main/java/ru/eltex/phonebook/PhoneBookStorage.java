package ru.eltex.phonebook;

import java.util.List;

/**
 * Interface representing a means of interaction with the phone book storage
 */
public interface PhoneBookStorage {
    /**
     * Gets list of all the users stored in the storage
     * @return List of {@link User}s stored in the storage
     * @throws Exception Could be thrown if something went wrong while trying to obtain users
     */
    List<User> getAllUsers() throws Exception;

    /**
     * Creates a new user and puts it in the storage
     * @param name The name to assign to the new user
     * @param phoneNumber The phone number to assign to the new user
     * @return The instance of created {@link User}
     * @throws Exception Could be thrown if something went wrong while trying to insert new user
     */
    User insertNewUser(String name, String phoneNumber) throws Exception;

    /**
     * Tries to remove an existing user by its ID from the storage
     * @param id The ID of the user to remove
     * @return {@literal true} if removed successfully, {@literal false} if user with provided ID was not found
     * @throws Exception Could be thrown if something went wrong while trying to remove the user
     */
    boolean removeUserById(int id) throws Exception;
}
