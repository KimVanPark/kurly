package com.kvp.kurly.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {

    Boolean existsByWorkerIdAndReasonAndDate(Long workerId, PointReason reason, LocalDate targetDate);
}
