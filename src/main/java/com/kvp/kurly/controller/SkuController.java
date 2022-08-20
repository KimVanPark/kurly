package com.kvp.kurly.controller;

import com.kvp.kurly.dto.SkuCreateRequest;
import com.kvp.kurly.dto.SkuResponse;
import com.kvp.kurly.dto.SkuUpdateRequest;
import com.kvp.kurly.service.SkuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/skus")
public class SkuController {
    private final SkuService skuService;

    public SkuController(SkuService skuService) {
        this.skuService = skuService;
    }

    @PostMapping
    public SkuResponse create(@RequestBody SkuCreateRequest request) {
        return SkuResponse.from(
            skuService.create(request)
        );
    }

    @GetMapping("/{skuId}")
    public SkuResponse getSku(@PathVariable Long skuId) {
        return SkuResponse.from(skuService.find(skuId));
    }

    @PutMapping("/{skuId}")
    public SkuResponse update(@PathVariable Long skuId, @RequestBody SkuUpdateRequest request) {
        return SkuResponse.from(skuService.update(skuId, request));
    }
}
