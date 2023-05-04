package com.hormigo.david.parkingmanager.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hormigo.david.parkingmanager.core.exceptions.UserDoesNotExistsException;
import com.hormigo.david.parkingmanager.core.exceptions.UserExistsException;
import com.hormigo.david.parkingmanager.user.domain.User;
import com.hormigo.david.parkingmanager.user.domain.UserDao;
import com.hormigo.david.parkingmanager.user.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public List<User> all() {
        return (List<User>) userService.getAll();
    }

    @GetMapping("/api/users/{id}")
    public User getById(@PathVariable Long id) throws UserDoesNotExistsException {
        return this.userService.getUser(id).orElseThrow(UserDoesNotExistsException::new);

    }

    @PostMapping("/api/users")
    public User createUser(@Valid @RequestBody UserDao userDao) throws UserExistsException {
        {
            User user = null;

            user = this.userService.register(userDao);

            return user;

        }

    }

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable long id) throws UserDoesNotExistsException {
        this.userService.deleteUserById(id);
    }
}
