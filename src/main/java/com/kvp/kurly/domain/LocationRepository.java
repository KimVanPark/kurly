package com.kvp.kurly.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsByCode(String code);

    Optional<Location> findByCode(String code);
}
