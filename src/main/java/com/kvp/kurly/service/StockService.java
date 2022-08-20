package com.kvp.kurly.service;

import com.kvp.kurly.domain.Location;
import com.kvp.kurly.domain.Sku;
import com.kvp.kurly.domain.Stock;
import com.kvp.kurly.domain.StockRepository;
import com.kvp.kurly.dto.StockReceiveRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StockService {
    private final SkuService skuService;
    private final LocationService locationService;
    private final StockRepository stockRepository;

    public StockService(SkuService skuService, LocationService locationService, StockRepository stockRepository) {
        this.skuService = skuService;
        this.locationService = locationService;
        this.stockRepository = stockRepository;
    }

    @Transactional
    public Stock receive(StockReceiveRequest request) {
        Stock stock = findOrCreate(request.getLocationId(), request.getSkuId());
        stock.receive(request.getQuantity());
        return stock;
    }

    public Stock findOrCreate(Long locationId, Long skuId) {
        return stockRepository.findByLocation_IdAndSku_Id(locationId, skuId)
            .orElseGet(() -> create(locationId, skuId));
    }

    private Stock create(Long locationId, Long skuId) {
        Location location = locationService.find(locationId);
        Sku sku = skuService.find(skuId);
        Stock stock = Stock.create(location, sku);
        return stockRepository.save(stock);
    }
}
