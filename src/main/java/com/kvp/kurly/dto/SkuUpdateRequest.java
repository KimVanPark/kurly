package com.kvp.kurly.dto;


import com.kvp.kurly.domain.SkuStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkuUpdateRequest {
    private SkuStatus status;
}
