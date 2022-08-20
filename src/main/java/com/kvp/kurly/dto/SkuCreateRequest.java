package com.kvp.kurly.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkuCreateRequest {
    private String name;
    private String code;
    private String barcode;
}
