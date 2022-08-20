package com.kvp.kurly.controller;

import com.kvp.kurly.dto.StockReceiveRequest;
import com.kvp.kurly.dto.StockResponse;
import com.kvp.kurly.service.StockService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/receive")
    public StockResponse receive(@Valid @RequestBody StockReceiveRequest request) {
        return StockResponse.from(
            stockService.receive(request)
        );
    }
}
