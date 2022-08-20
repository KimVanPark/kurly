package com.kvp.kurly.domain;

import lombok.Getter;

@Getter
public enum SkuStatus {
    READY("런칭 예정"),
    SALE("판매 중"),
    STOP("판매 중지");
    private final String description;

    SkuStatus(String description) {
        this.description = description;
    }
}
