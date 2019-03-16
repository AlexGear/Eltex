package ru.eltex.phonebook;

public class IdProvidingException extends Exception {
    public IdProvidingException(Throwable cause) {
        super("Failed to provide id", cause);
    }
}
