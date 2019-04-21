package ru.eltex.phonebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

/**
 * Main class with method {@code main}.
 */
@SpringBootApplication
public class App {
    /**
     * Starts the Spring Application and opens the phone book console menu
     * @param args The program args
     */
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        PhoneBook.INSTANCE.enterMenu();
        SpringApplication.exit(context);
    }
}