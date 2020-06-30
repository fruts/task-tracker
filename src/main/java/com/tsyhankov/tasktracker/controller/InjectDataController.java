package com.tsyhankov.tasktracker.controller;

import com.tsyhankov.tasktracker.entity.Status;
import com.tsyhankov.tasktracker.entity.Task;
import com.tsyhankov.tasktracker.entity.User;
import com.tsyhankov.tasktracker.service.StatusService;
import com.tsyhankov.tasktracker.service.TaskService;
import com.tsyhankov.tasktracker.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;

@Controller
public class InjectDataController {

    private final StatusService statusService;
    private final UserService userService;
    private final TaskService taskService;

    public InjectDataController(StatusService statusService,
                                UserService userService, TaskService taskService) {
        this.statusService = statusService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @PostConstruct
    public void init() {
        injectStatuses();
        injectTasks();
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
        admin.setTasks(Set.of(taskService.getTaskById(1L)));
        User user = new User();
        user.setEmail("user");
        user.setPassword("123");
        user.setRole("USER");
        user.setTasks(Set.of(taskService.getTaskById(2L)));
        userService.saveUser(admin);
        userService.saveUser(user);
    }

    private void injectTasks() {
        Task taskOne = new Task();
        taskOne.setTitle("Task1");
        taskOne.setDescription("for job");
        taskService.saveTask(taskOne);
        Task taskTwo = new Task();
        taskTwo.setTitle("task2");
        taskTwo.setDescription("java skills");
        taskService.saveTask(taskTwo);
    }

}
