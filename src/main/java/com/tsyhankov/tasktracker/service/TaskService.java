package com.tsyhankov.tasktracker.service;

import com.tsyhankov.tasktracker.entity.Task;
import com.tsyhankov.tasktracker.exception.DataProcessingException;
import com.tsyhankov.tasktracker.repository.TaskRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private StatusService statusService;

    public Task saveTask(Task task) {
        task.setStatus(statusService.getStatusByName("View"));
        return taskRepository.save(task);
    }

    public List<Task> saveTasks(List<Task> tasks) {
        tasks.forEach(t -> t.setStatus(statusService.getStatusByName("View")));
        return taskRepository.saveAll(tasks);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(()
                -> new DataProcessingException("Unable to find task with id: " + taskId));
    }

    public void deleteTaskByTaskId(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task updateTask(Task task) {
        Task existingTask = getTaskById(task.getId());
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        return taskRepository.save(existingTask);
    }
}
