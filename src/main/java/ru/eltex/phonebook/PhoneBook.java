package ru.eltex.phonebook;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBook {
    private final String filename;
    private ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) throws IOException {
	    PhoneBook phoneBook = new PhoneBook("phonebook.csv");
	    phoneBook.enterMenu();
	    phoneBook.save();
    }

    public PhoneBook(String filename) throws IOException {
        this.filename = filename;

        try (FileReader reader = new FileReader(filename);
             Scanner scanner = new Scanner(reader))
        {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                User user = new User();
                user.initWithCSV(line);
                users.add(user);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("File '" + filename + "' was not found. A new one will be created on exit.");
            return;
        }
        catch(IOException e) {
            System.err.println("Couldn't open file '" + filename + "'");
            throw e;
        }
    }

    public void enterMenu() {
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

    public void save() throws IOException {
        try(FileWriter writer = new FileWriter(filename)) {
            for(User user : users) {
                writer.write(user.toCSV() + "\n");
            }
        }
        catch(IOException e) {
            System.err.println("Failed to save to file '" + filename + "'");
            throw e;
        }
    }

    private int askOption() {
        Scanner in = new Scanner(System.in);
        System.out.println("Phone book menu:");
        while (true) {
            System.out.println("  1. List users\n  2. Create new user\n  3. Remove user\n  0. Save and Exit");
            System.out.println("Enter option:");

            int option = in.nextInt();
            if(option >= 0 && option <= 3)
                return option;

            System.out.println("Please, enter correct option");
        }
    }

    private void listUsers() {
	    if(users.size() == 0) {
	        System.out.println("No users\n");
	        return;
        }

	    System.out.printf("%3s %25s %20s\n", "ID", "Name", "Phone Number");
        for(User user : users) {
            System.out.printf("%3d %25s %20d\n", user.getId(), user.getName(), user.getPhoneNumber());
        }
        System.out.println();
    }

    private void createNewUser() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter new user's name:");
        String name = in.nextLine();
        System.out.println("Enter new user's phone number:");
        long phoneNumber = in.nextLong();

        User newUser = new User(name, phoneNumber);
        users.add(newUser);

        System.out.println("User created successfully\n");
    }

    private void removeUser() {
        Scanner in = new Scanner(System.in);

        while(true) {
            System.out.println("Enter ID of user to remove ('-1' to cancel):");
            int id = in.nextInt();
            if(id == -1)
                return;

            if(tryRemoveUser(id)) {
                System.out.println("User removed successfully\n");
                return;
            }
            System.out.printf("User of ID %d was not found. Please, try again\n", id);
        }
    }

    private boolean tryRemoveUser(int id) {
	    for(int i = 0; i < users.size(); i++) {
	        User user = users.get(i);
	        if(id == user.getId()) {
	            users.remove(i);
	            return true;
            }
        }
	    return false;
    }
} 