package com.kvp.kurly.service;

import com.kvp.kurly.domain.Worker;
import com.kvp.kurly.domain.WorkerRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {

    private final WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public Worker create(String name) {
        return workerRepository.save(Worker.of(name));
    }

    public Worker find(Long workerId) {
        return workerRepository.findById(workerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 작업자입니다."));
    }
}
