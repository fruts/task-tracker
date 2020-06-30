package com.tsyhankov.tasktracker.controller;

import com.tsyhankov.tasktracker.entity.User;
import com.tsyhankov.tasktracker.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/admin/users/add")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/admin/users/addusers")
    public List<User> addUsers(@RequestBody List<User> users) {
        return userService.saveUsers(users);
    }

    @GetMapping("/users/id/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/users/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/admin/users/update")
    public User updateUser(@RequestBody User user) {
        return userService.userUpdate(user);
    }

    @PutMapping("/users/updatetasks")
    public User updateUserTasks(@RequestBody User user) {
        return userService.updateUserTasks(user);
    }

    @DeleteMapping("/admin/users/delete/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }
}
