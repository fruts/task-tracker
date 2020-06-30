package com.tsyhankov.tasktracker.repository;

import com.tsyhankov.tasktracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
