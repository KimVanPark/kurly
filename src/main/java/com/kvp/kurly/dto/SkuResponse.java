package com.kvp.kurly.dto;

import com.kvp.kurly.domain.Sku;
import com.kvp.kurly.domain.SkuStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkuResponse {
    private Long id;
    private String name;
    private String code;
    private SkuStatus status;
    private String barcode;

    public static SkuResponse from(Sku sku) {
        return new SkuResponse(
            sku.getId(),
            sku.getName(),
            sku.getCode(),
            sku.getStatus(),
            sku.getBarcode()
        );
    }
}
