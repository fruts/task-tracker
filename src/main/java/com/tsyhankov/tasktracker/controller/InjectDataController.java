package com.tsyhankov.tasktracker.controller;

import com.tsyhankov.tasktracker.entity.Status;
import com.tsyhankov.tasktracker.entity.User;
import com.tsyhankov.tasktracker.service.StatusService;
import com.tsyhankov.tasktracker.service.UserService;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;

@Controller
public class InjectDataController {

    private final StatusService statusService;
    private final UserService userService;

    public InjectDataController(StatusService statusService, UserService userService) {
        this.statusService = statusService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        injectStatuses();
        injectUsers();
    }

    private void injectStatuses() {
        Status viewStatus = new Status();
        viewStatus.setStatusName("View");
        Status inProgressStatus = new Status();
        inProgressStatus.setStatusName("In Progress");
        Status completeStatus = new Status();
        completeStatus.setStatusName("Complete");
        statusService.addStatus(viewStatus);
        statusService.addStatus(inProgressStatus);
        statusService.addStatus(completeStatus);
    }

    private void injectUsers() {
        User admin = new User();
        admin.setEmail("serj@gmail.com");
        admin.setPassword("123");
        admin.setRole("ADMIN");
        User user = new User();
        user.setEmail("user");
        user.setPassword("123");
        user.setRole("USER");
        userService.saveUser(admin);
        userService.saveUser(user);
    }

}
