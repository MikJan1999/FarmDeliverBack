package com.example.demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/control")
@RequiredArgsConstructor
public class TestControl {
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Próba dostania się na endpoint");
    }


}
