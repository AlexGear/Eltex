package ru.eltex.phonebook;

public interface IdProvider {
    int getId() throws IdProvidingException;
}
