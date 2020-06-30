package com.tsyhankov.tasktracker.repository;

import com.tsyhankov.tasktracker.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {

    Status findByStatusName(String statusName);
}
