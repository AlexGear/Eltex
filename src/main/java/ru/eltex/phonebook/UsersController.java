package ru.eltex.phonebook;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {
    @GetMapping("/phonebook")
    public List<User> getUsers() {
        return PhoneBook.getInstance().getUsers();
    }
}
