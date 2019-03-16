package ru.eltex.phonebook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVStorage extends PhoneBookStorage implements IdProvider {
    private final String csvFileName;

    public CSVStorage(PhoneBook phoneBook, String csvFileName) throws IOException {
        super(phoneBook);
        this.csvFileName = csvFileName;

        File file = new File(csvFileName);
        if(!file.exists()){
            file.createNewFile();
        }
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        try (FileReader reader = new FileReader(csvFileName); Scanner scanner = new Scanner(reader)) {
            List<User> users = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                User user = new User();
                user.initWithCSV(line);
                users.add(user);
            }
            return users;
        }
    }

    @Override
    public User insertNewUser(String name, String phoneNumber) throws IOException, IdProvidingException {
        User newUser = new User(this, name, phoneNumber);

        try(FileWriter writer = new FileWriter(csvFileName, true)) {
            writer.write(newUser.toCSV() + "\n");
            return newUser;
        }
        catch(IOException e) {
            System.err.println("Failed to save to file '" + csvFileName + "'");
            throw e;
        }
    }

    @Override
    public boolean removeUserById(int id) throws Exception {
        List<User> users = getAllUsers();
        if(!users.removeIf(user -> user.getId() == id)) {
            return false;
        }
        writeToFile(users);
        return true;
    }

    @Override
    public int getId() throws IdProvidingException {
        int maxId = -1;
        try {
            for(User user : getAllUsers()) {
                if(user.getId() > maxId) {
                    maxId = user.getId();
                }
            }
        } catch (Exception e) {
            throw new IdProvidingException(e);
        }
        return maxId + 1;
    }

    private void writeToFile(List<User> users) throws IOException {
        try(FileWriter writer = new FileWriter(csvFileName)) {
            for(User user : users) {
                writer.write(user.toCSV() + "\n");
            }
        }
        catch(IOException e) {
            System.err.println("Failed to save to file '" + csvFileName + "'");
            throw e;
        }
    }
}
