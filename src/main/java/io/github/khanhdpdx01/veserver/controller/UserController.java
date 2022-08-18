package io.github.khanhdpdx01.veserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class UserController {
    @GetMapping
    public ResponseEntity<?> getAdmin() {
        return ResponseEntity.status(200).body("OK");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.status(200).body("OK2");
    }
}
