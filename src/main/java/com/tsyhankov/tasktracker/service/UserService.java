package com.tsyhankov.tasktracker.service;

import com.tsyhankov.tasktracker.entity.User;
import com.tsyhankov.tasktracker.exception.DataProcessingException;
import com.tsyhankov.tasktracker.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> saveUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()
                -> new DataProcessingException("Unable to get user with id: " + userId));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    public User userUpdate(User user) {
        User existingUser = getUserByEmail(user.getEmail());
        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setRole(user.getRole());
        existingUser.setTasks(user.getTasks());
        return userRepository.save(existingUser);
    }

    public User updateUserTasks(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        existingUser.setTasks(user.getTasks());
        return userRepository.save(existingUser);
    }

}
