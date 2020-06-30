package com.tsyhankov.tasktracker.controller;

import com.tsyhankov.tasktracker.entity.Task;
import com.tsyhankov.tasktracker.service.StatusService;
import com.tsyhankov.tasktracker.service.TaskService;
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
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private StatusService statusService;

    @PostMapping("/tasks/add")
    public Task addTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @PostMapping("/tasks/addtasks")
    public List<Task> addTasks(@RequestBody List<Task> tasks) {
        return taskService.saveTasks(tasks);
    }

    @GetMapping("/tasks/all")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @PutMapping("/tasks/update")
    public Task updateTask(@RequestBody Task task) {
        return taskService.updateTask(task);
    }

    @DeleteMapping("/tasks/delete/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTaskByTaskId(taskId);
    }

}
