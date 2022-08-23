package com.kvp.kurly.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", nullable = false)
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    public Location() {
    }

    public Location(String code) {
        this.code = code;
    }

    public boolean isSameCode(String code) {
        return this.code.equals(code);
    }
}
