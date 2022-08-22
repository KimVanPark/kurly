package com.kvp.kurly.controller;

import com.kvp.kurly.dto.WorkerCreateRequest;
import com.kvp.kurly.dto.WorkerResponse;
import com.kvp.kurly.service.WorkerService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/workers")
@RestController
public class WorkerController {

    private WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping
    public WorkerResponse create(@RequestBody WorkerCreateRequest workerCreateRequest) {
        return WorkerResponse.from(workerService.create(workerCreateRequest.getName()));
    }

    @GetMapping("/{workerId}")
    public WorkerResponse findById(@PathVariable Long workerId) {
        return WorkerResponse.from(workerService.find(workerId));
    }
}
