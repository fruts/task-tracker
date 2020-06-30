package com.tsyhankov.tasktracker.controller;

import com.tsyhankov.tasktracker.entity.Status;
import com.tsyhankov.tasktracker.service.StatusService;
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
public class StatusController {
    @Autowired
    private StatusService statusService;

    @PostMapping("/admin/statuses/add")
    public Status addStatus(@RequestBody Status status) {
        return statusService.addStatus(status);
    }

    @PostMapping("/admin/statuses/addstatuses")
    public List<Status> addStatuses(@RequestBody List<Status> statuses) {
        return statusService.addStatuses(statuses);
    }

    @GetMapping("/admin/statuses")
    public List<Status> getAllStatuses() {
        return statusService.getAllStatuses();
    }

    @GetMapping("/statuses/id/{statusId}")
    public Status getStatusById(@PathVariable Long statusId) {
        return statusService.getStatusById(statusId);
    }

    @GetMapping("/statuses/name/{statusName}")
    public Status getStatusByName(@PathVariable String statusName) {
        return statusService.getStatusByName(statusName);
    }

    @PutMapping("/admin/statuses/update")
    public Status updateStatus(@RequestBody Status status) {
        return statusService.updateStatus(status);
    }

    @DeleteMapping("/admin/statuses/delete/{statusId}")
    public void deleteStatus(@PathVariable Long statusId) {
        statusService.deleteStatusById(statusId);
    }
}
