package com.kvp.kurly.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Sku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sku_id", nullable = false)
    private Long id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SkuStatus status;

    @Column(name = "barcode", length = 20, unique = true, nullable = false)
    private String barcode;

    public Sku() {
    }

    public Sku(String name, String code, SkuStatus status, String barcode) {
        this.name = name;
        this.code = code;
        this.status = status;
        this.barcode = barcode;
    }

    public static Sku of(String name, String code, SkuStatus status, String barcode) {
        return new Sku(name, code, status, barcode);
    }

    public void update(SkuStatus status) {
        this.status = status;
    }

    public boolean isSameBarcode(String barcode) {
        return this.barcode.equals(barcode);
    }
}
