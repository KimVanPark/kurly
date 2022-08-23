package com.kvp.kurly.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_id", nullable = false)
    private Sku sku;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    private Stock() {
    }

    public Stock(Location location, Sku sku, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("재고 수량은 0 이상이여야 합니다.");
        }
        this.location = location;
        this.sku = sku;
        this.quantity = quantity;
    }

    public static Stock create(Location location, Sku sku) {
        return new Stock(location, sku, 0);
    }

    public void receive(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("입고 수량은 0 이상이여야 합니다.");
        }
        this.quantity += quantity;
    }
}
