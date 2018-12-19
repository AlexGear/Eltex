package ru.eltex.phonebook;

public interface CSV {
    String toCSV();
    void initWithCSV(String csvLine);
}
