package com.kvp.kurly.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockReceiveRequest {
    private Long locationId;
    private Long skuId;
    @Positive(message = "입고 수량은 양수이어야 합니다.")
    private int quantity;
}
