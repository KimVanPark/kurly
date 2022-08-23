package com.kvp.kurly.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByLocation_IdAndSku_Id(Long locationId, Long skuId);
}
