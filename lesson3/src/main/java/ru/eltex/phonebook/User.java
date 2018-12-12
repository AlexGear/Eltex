package ru.eltex.phonebook;

public class User {
    private static int lastId = 0;

    private final int id;
    private String name;
    private long phoneNumber;

    private static int engageId() {
        return lastId++;
    }

    public User(String name, long phoneNumber) {
        id = engageId();
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}