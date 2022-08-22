package com.kvp.kurly.dto;

import com.kvp.kurly.domain.Worker;
import com.kvp.kurly.domain.WorkerLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkerResponse {
    private Long id;
    private String name;
    private WorkerLevel level;

    public static WorkerResponse from(Worker worker) {
        return new WorkerResponse(
                worker.getId(),
                worker.getName(),
                worker.getLevel()
        );
    }
}
