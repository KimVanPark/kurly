package com.kvp.kurly.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Worker {

    public static final int BASE_POINT = 300;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private WorkerLevel level;

    private int point;

    private Worker(String name) {
        isValidName(name);
        this.name = name;
        this.level = WorkerLevel.LOW;
        this.point = 100;
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

    public void levelDown() {
        this.level = WorkerLevel.LOW;
    }
    
    public PointHistory addPoint(int point, PointReason reason) {
        this.point += point;
        if (this.point >= BASE_POINT) {
            levelUp();
        }
        return new PointHistory(this.id, point, reason);
    }
    
    public PointHistory subtractPoint(int point, PointReason reason) {
        this.point -= point;
        if (this.point < BASE_POINT) {
            levelDown();
        }
        return new PointHistory(this.id, -point, reason);
    }

}