package ru.eltex.phonebook;

public class User {
    private int id;
    private String name;
    private String phoneNumber;

    public User() {
    }

    public User(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name.trim();
        this.phoneNumber = phoneNumber.trim();
    }

    public int getId() {
        return id;
    }

    private void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber.trim();
    }
}