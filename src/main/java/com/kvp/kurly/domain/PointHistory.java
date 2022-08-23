package com.kvp.kurly.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long workerId;

    private LocalDate date;

    private int point;

    @Enumerated(EnumType.STRING)
    private PointReason reason;

    public PointHistory(Long workerId, int point, PointReason reason) {
        this.workerId = workerId;
        this.date = LocalDate.now();
        this.point = point;
        this.reason = reason;
    }
}
