package com.tsyhankov.tasktracker.service;

import com.tsyhankov.tasktracker.entity.Status;
import com.tsyhankov.tasktracker.exception.DataProcessingException;
import com.tsyhankov.tasktracker.repository.StatusRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public Status addStatus(Status status) {
        return statusRepository.save(status);
    }

    public List<Status> addStatuses(List<Status> statuses) {
        return statusRepository.saveAll(statuses);
    }

    public Status getStatusById(Long statusId) {
        return statusRepository.findById(statusId).orElseThrow(()
                -> new DataProcessingException("Unable to get status with id: " + statusId));
    }

    public Status getStatusByName(String statusName) {
        return statusRepository.findByStatusName(statusName);
    }

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public void deleteStatusById(Long statusId) {
        statusRepository.deleteById(statusId);
    }

    public Status updateStatus(Status status) {
        Status existingStatus = getStatusById(status.getId());
        existingStatus.setStatusName(status.getStatusName());
        return statusRepository.save(existingStatus);
    }

}
