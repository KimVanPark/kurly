package com.kvp.kurly.service;

import com.kvp.kurly.domain.Location;
import com.kvp.kurly.domain.LocationRepository;
import com.kvp.kurly.domain.Sku;
import com.kvp.kurly.dto.LocationCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location create(LocationCreateRequest request) {
        if (locationRepository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException(String.format("Location 코드(%s)가 이미 존재합니다.", request.getCode()));
        }

        Location location = new Location(request.getCode());

        return locationRepository.save(location);
    }

    @Transactional(readOnly = true)
    public Location find(Long locationId) {
        return locationRepository.findById(locationId)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Location(ID: %s)를 찾을 수 없습니다.", locationId)));
    }
}
