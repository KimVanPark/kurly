package com.kvp.kurly.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private WorkerLevel level;

    private Worker(String name) {
        isValidName(name);
        this.name = name;
        this.level = WorkerLevel.LOW;
    }

    public static Worker of(String name) {
        return new Worker(name);
    }

    public void isValidName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("피킹시작을 위해 작업자를 입력해주세요.");
        }
    }

    public void levelUp() {
        this.level = WorkerLevel.HIGH;
    }

}