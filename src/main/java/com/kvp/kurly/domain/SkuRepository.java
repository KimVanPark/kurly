package com.kvp.kurly.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkuRepository extends JpaRepository<Sku, Long> {
    boolean existsByCode(String code);

    Optional<Sku> findByCode(String code);
}
