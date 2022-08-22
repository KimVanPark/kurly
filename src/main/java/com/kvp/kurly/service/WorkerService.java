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

    public Worker findByName(String name) {
        return workerRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 작업자입니다."));
    }

    public Worker addPoint(Long workerId, int point) {
        Worker worker = find(workerId);
        worker.addPoint(point);
        return worker;
    }

    public Worker subtractPoint(Long workerId, int point) {
        Worker worker = find(workerId);
        worker.subtractPoint(point);
        return worker;
    }
}
