package ru.eltex.phonebook;

public class User implements CSV {
    private int id;
    private String name;
    private long phoneNumber;

    public User() {
    }

    public User(IdProvider idProvider, String name, long phoneNumber) {
        id = idProvider.getId();
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toCSV() {
        return String.format("%d;%s;%d", id, name, phoneNumber);
    }

    @Override
    public void initWithCSV(String csvLine) {
        String[] args = csvLine.split(";");
        if(args.length != 3) {
            throw new IllegalArgumentException(csvLine);
        }
        setId(Integer.parseInt(args[0]));
        setName(args[1]);
        setPhoneNumber(Long.parseLong(args[2]));
    }
}