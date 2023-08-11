package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRestControllerTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    void ignoredForbiddenNotAuthenticated() {
        ResponseEntity<String> response = restTemplate.getForEntity("/ignored-api/forbidden", String.class);
        assertThat(response.getStatusCode()).as(response.toString()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void ignoredForbiddenAuthenticated() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "test")
                .getForEntity("/ignored-api/forbidden", String.class);
        assertThat(response.getStatusCode()).as(response.toString()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void ignoredOkNotAuthenticated() {
        ResponseEntity<String> response = restTemplate.getForEntity("/ignored-api/ok", String.class);
        assertThat(response.getStatusCode()).as(response.toString()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void ignoredOkAuthenticated() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "test")
                .getForEntity("/ignored-api/ok", String.class);
        assertThat(response.getStatusCode()).as(response.toString()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void ignoredUnknownNotAuthenticated() {
        ResponseEntity<String> response = restTemplate.getForEntity("/ignored-api/dummy", String.class);
        assertThat(response.getStatusCode()).as(response.toString()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void ignoredUnknownAuthenticated() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "test")
                .getForEntity("/ignored-api/dummy", String.class);
        assertThat(response.getStatusCode()).as(response.toString()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void allowedForbiddenNotAuthenticated() {
        ResponseEntity<String> response = restTemplate.getForEntity("/allowed-api/forbidden", String.class);
        assertThat(response.getStatusCode()).as(response.toString()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void allowedForbiddenAuthenticated() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "test")
                .getForEntity("/allowed-api/forbidden", String.class);
        assertThat(response.getStatusCode()).as(response.toString()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void allowedOkNotAuthenticated() {
        ResponseEntity<String> response = restTemplate.getForEntity("/allowed-api/ok", String.class);
        assertThat(response.getStatusCode()).as(response.toString()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void allowedOkAuthenticated() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "test")
                .getForEntity("/allowed-api/ok", String.class);
        assertThat(response.getStatusCode()).as(response.toString()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void allowedUnknownNotAuthenticated() {
        ResponseEntity<String> response = restTemplate.getForEntity("/allowed-api/dummy", String.class);
        assertThat(response.getStatusCode()).as(response.toString()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void allowedUnknownAuthenticated() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "test")
                .getForEntity("/allowed-api/dummy", String.class);
        assertThat(response.getStatusCode()).as(response.toString()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
