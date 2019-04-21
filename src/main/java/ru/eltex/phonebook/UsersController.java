package ru.eltex.phonebook;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest controller used for obtaining phone book users from the front-end via '/phonebook' URL
 */
@RestController
public class UsersController {
    /**
     * Obtains users stored in the phone book
     * @return List of {@link User}s stored in the phone book
     */
    @GetMapping("/phonebook")
    public List<User> getUsers() {
        return PhoneBook.INSTANCE.getUsers();
    }
}
