package com.mbami.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HealthController {

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }

    @RequestMapping(value = "/health", method = RequestMethod.HEAD)
    public ResponseEntity<Void> healthHeadCheck() {
        return ResponseEntity.ok().build();
    }
}