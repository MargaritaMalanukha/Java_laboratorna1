package org.margomalanuha.spring.labs.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping()
    public ResponseEntity<String> checkServerWorks() {
        return ResponseEntity.ok().build();
    }

}
