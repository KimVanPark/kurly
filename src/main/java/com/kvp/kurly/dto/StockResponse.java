package com.kvp.kurly.dto;

import com.kvp.kurly.domain.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockResponse {
    private Long stockId;
    private Long locationId;
    private Long skuId;
    private int quantity;

    public static StockResponse from(Stock stock) {
        return new StockResponse(
            stock.getId(),
            stock.getLocation().getId(),
            stock.getSku().getId(),
            stock.getQuantity()
        );
    }
}
