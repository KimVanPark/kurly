package com.kvp.kurly.service;

import com.kvp.kurly.domain.*;
import org.springframework.stereotype.Service;

@Service
public class WorkerService {

    private final WorkerRepository workerRepository;

    private final PointHistoryRepository pointHistoryRepository;

    public WorkerService(WorkerRepository workerRepository, PointHistoryRepository pointHistoryRepository) {
        this.workerRepository = workerRepository;
        this.pointHistoryRepository = pointHistoryRepository;
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

    public Worker addPoint(Long workerId, int point, PointReason reason) {
        Worker worker = find(workerId);
        PointHistory pointHistory = worker.addPoint(point, reason);
        pointHistoryRepository.save(pointHistory);
        return worker;
    }

    public Worker subtractPoint(Long workerId, int point, PointReason reason) {
        Worker worker = find(workerId);
        PointHistory pointHistory = worker.subtractPoint(point, reason);
        pointHistoryRepository.save(pointHistory);
        return worker;
    }
}
