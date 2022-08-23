package com.kvp.kurly.controller;

import com.kvp.kurly.dto.LocationCreateRequest;
import com.kvp.kurly.dto.LocationResponse;
import com.kvp.kurly.service.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public LocationResponse create(@RequestBody LocationCreateRequest request) {
        return LocationResponse.from(
            locationService.create(request)
        );
    }

    @GetMapping("/{locationId}")
    public LocationResponse getLocation(@PathVariable Long locationId) {
        return LocationResponse.from(locationService.find(locationId));
    }
}
