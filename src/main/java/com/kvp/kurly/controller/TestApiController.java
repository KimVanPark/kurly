package com.kvp.kurly.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/test")
@RestController
public class TestApiController {
    @GetMapping
    public String test() {
        return UUID.randomUUID().toString();
    }
}
