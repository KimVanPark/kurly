package com.kvp.kurly.dto;

import com.kvp.kurly.domain.Worker;
import com.kvp.kurly.domain.WorkerLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkerResponse {
    private Long id;
    private String name;
    private WorkerLevel level;
    private int point;
    private List<PointResponse> points;

    public static WorkerResponse from(Worker worker) {
        return new WorkerResponse(
            worker.getId(),
            worker.getName(),
            worker.getLevel(),
            worker.getPoint(),
            new ArrayList<>()
        );
    }

    public static WorkerResponse of(Worker worker, List<PointResponse> pointResponses) {
        return new WorkerResponse(
            worker.getId(),
            worker.getName(),
            worker.getLevel(),
            worker.getPoint(),
            pointResponses
        );
    }
}
