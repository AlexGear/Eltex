package ru.eltex.phonebook;

import java.util.Objects;

/**
 * Class representing a user stored in the phone book
 */
public class User {
    private int id;
    private String name;
    private String phoneNumber;

    /**
     * Allocates a new user object and initializes its fields with passed params
     * @param id The ID of the new user
     * @param name The name of the new user
     * @param phoneNumber The phone number of the new user
     */
    public User(int id, String name, String phoneNumber) throws IllegalArgumentException {
        setId(id);
        setName(name);
        setPhoneNumber(phoneNumber);
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    /**
     * Sets the user's name
     * @param name The name to assign to the user
     * @throws IllegalArgumentException Thrown if the name param is null or blank
     */
    public void setName(String name) throws IllegalArgumentException {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name is null or blank");
        }
        this.name = name.trim();
    }

    public String getPhoneNumber() { return phoneNumber; }

    /**
     * Sets the user's phone number
     * @param phoneNumber The phone number ot assign to the user
     * @throws IllegalArgumentException Thrown if the phone number param is null or blank
     */
    public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if(phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("phoneNumber is null or blank");
        }
        this.phoneNumber = phoneNumber.trim();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User) {
            User other = (User)obj;
            return id == other.id && name.equals(other.name) && phoneNumber.equals(other.phoneNumber);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber);
    }
}