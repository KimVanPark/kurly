package com.kvp.kurly.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Long>  {

    Optional<Worker> findByName(String name);
}
