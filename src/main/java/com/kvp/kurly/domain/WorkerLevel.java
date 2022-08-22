package com.kvp.kurly.domain;

public enum WorkerLevel {
    LOW("미숙련자"),
    HIGH("숙련자");

    private final String level;

    WorkerLevel(String level) {
        this.level = level;
    }
}
