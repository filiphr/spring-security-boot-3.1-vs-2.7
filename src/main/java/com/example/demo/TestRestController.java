package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @GetMapping("/ignored-api/forbidden")
    public ResponseEntity<?> ignoredForbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @GetMapping("/ignored-api/ok")
    public ResponseEntity<?> ignoredOk() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/allowed-api/forbidden")
    public ResponseEntity<?> allowedForbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @GetMapping("/allowed-api/ok")
    public ResponseEntity<?> allowedOk() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
