package com.kvp.kurly.controller;

import com.kvp.kurly.domain.Worker;
import com.kvp.kurly.dto.PointResponse;
import com.kvp.kurly.dto.WorkerCreateRequest;
import com.kvp.kurly.dto.WorkerResponse;
import com.kvp.kurly.service.PointService;
import com.kvp.kurly.service.WorkerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/workers")
@RestController
public class WorkerController {

    private final WorkerService workerService;
    private final PointService pointService;

    public WorkerController(WorkerService workerService, PointService pointService) {
        this.workerService = workerService;
        this.pointService = pointService;
    }

    @PostMapping
    public WorkerResponse create(@RequestBody WorkerCreateRequest workerCreateRequest) {
        return WorkerResponse.from(workerService.create(workerCreateRequest.getName()));
    }

    @GetMapping("/{workerId}")
    public WorkerResponse findById(@PathVariable Long workerId) {
        return WorkerResponse.from(workerService.find(workerId));
    }

    @GetMapping
    public WorkerResponse login(String name) {
        Worker worker = workerService.findByName(name);
        List<PointResponse> pointResponses = pointService.adjustBeforePicking(worker);
        return WorkerResponse.of(workerService.findByName(name), pointResponses);
    }
}
