package ru.eltex.phonebook;

public class User {
    private int id;
    private String name;
    private String phoneNumber;

    public User(int id, String name, String phoneNumber) {
        setId(id);
        setName(name);
        setPhoneNumber(phoneNumber);
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name is null or blank");
        }
        this.name = name.trim();
    }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) {
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
}