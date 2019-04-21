package ru.eltex.phonebook;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Singleton class for working with the phone book.
 */
public class PhoneBook {
    public static final PhoneBook INSTANCE = new PhoneBook();

    private final PhoneBookStorage storage;

    /**
     * Gets all the {@link User}s stored in the phone book
     * @return List of {@link User}s stored in the phone book
     */
    public List<User> getUsers() {
        try {
            return storage.getAllUsers();
        } catch (Exception e) {
            System.err.println("Failed to obtain users");
            return Collections.emptyList();
        }
    }

    private PhoneBook() {
        storage = new DBStorage("users");
    }

    /**
     * Opens the console menu for user-interaction with the phone book and waits for inputs.
     * The options are:
     * 1. List all the users
     * 2. Create new user
     * 3. Remove existing user
     * 4. Leave the menu
     * The execution is returned when the user leaves the menu
     */
    void enterMenu() {
        while (true) {
            int option = askOption();
            System.out.println();
            switch (option) {
                case 1: listUsers(); break;
                case 2: createNewUser(); break;
                case 3: removeUser(); break;
                case 0: return;
            }
        }
    }

    /**
     * Prints the menu entries and waits for input
     * @return The option number that the user selected
     */
    private int askOption() {
        Scanner in = createStdinScanner();
        System.out.println("Phone book menu:");
        while (true) {
            System.out.println("  1. List users\n  2. Create new user\n  3. Remove users\n  0. Exit");
            System.out.println("Enter option:");

            try {
                int option = in.nextInt();
                if(option >= 0 && option <= 3)
                    return option;
            } catch (Exception ignored) {
                in = createStdinScanner();
            }

            System.out.println("Please, enter correct option");
        }
    }

    /**
     * Prints all the users stored in the phone book
     */
    private void listUsers() {
        List<User> users = getUsers();
	    if(users.size() == 0) {
	        System.out.println("No users");
	        return;
        }

	    System.out.printf("%3s %25s %20s%n", "ID", "Name", "Phone Number");
        for(User user : users) {
            System.out.printf("%3d %25s %20s%n", user.getId(), user.getName(), user.getPhoneNumber());
        }
        System.out.println();
    }

    /**
     * Starts the dialog for user creation
     */
    private void createNewUser() {
        Scanner in = createStdinScanner();

        System.out.println("Enter new user's name:");
        String name = in.nextLine();
        System.out.println("Enter new user's phone number:");
        String phoneNumber = in.nextLine();

        try {
            storage.insertNewUser(name, phoneNumber);
            System.out.println("User created successfully\n");
        } catch (Exception e) {
            System.err.println("Failed to create a new user");
        }
    }

    /**
     * Starts the dialog for user removal
     */
    private void removeUser() {
        Scanner in = createStdinScanner();

        while(true) {
            System.out.println("Enter ID of user to remove ('-1' to cancel):");

            int id;
            try {
                if((id = in.nextInt()) == -1) {
                    return;
                }
            } catch (Exception ignored) {
                in = createStdinScanner();
                continue;
            }

            try {
                if(storage.removeUserById(id)) {
                    System.out.println("User removed successfully\n");
                }
                else {
                    System.out.printf("User of ID %d was not found. Please, try again%n", id);
                }
            } catch (Exception e) {
                System.err.println("Failed to remove the user");
            }
        }
    }

    private static Scanner createStdinScanner() {
        return new Scanner(System.in, StandardCharsets.UTF_8);
    }
}