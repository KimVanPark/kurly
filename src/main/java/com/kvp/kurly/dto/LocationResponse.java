package com.kvp.kurly.dto;

import com.kvp.kurly.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponse {
    private Long id;
    private String code;

    public static LocationResponse from(Location location) {
        return new LocationResponse(
            location.getId(),
            location.getCode()
        );
    }
}
