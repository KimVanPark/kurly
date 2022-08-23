package com.kvp.kurly.service;

import com.kvp.kurly.domain.Sku;
import com.kvp.kurly.domain.SkuRepository;
import com.kvp.kurly.domain.SkuStatus;
import com.kvp.kurly.dto.SkuCreateRequest;
import com.kvp.kurly.dto.SkuUpdateRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SkuService {
    private final SkuRepository skuRepository;

    public SkuService(SkuRepository skuRepository) {
        this.skuRepository = skuRepository;
    }

    public Sku create(SkuCreateRequest request) {
        if (skuRepository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException(String.format("Sku 코드(%s)가 이미 존재합니다.", request.getCode()));
        }

        Sku sku = Sku.of(
            request.getName(),
            request.getCode(),
            SkuStatus.READY,
            request.getBarcode()
        );

        return skuRepository.save(sku);
    }

    public Sku find(Long skuId) {
        return skuRepository.findById(skuId)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Sku(ID: %s)를 찾을 수 없습니다.", skuId)));
    }

    @Transactional
    public Sku update(Long skuId, SkuUpdateRequest request) {
        Sku sku = find(skuId);

        sku.update(request.getStatus());

        return sku;
    }
}
