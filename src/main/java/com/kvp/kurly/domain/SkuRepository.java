package com.kvp.kurly.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SkuRepository extends JpaRepository<Sku, Long> {
    boolean existsByCode(String code);
}
