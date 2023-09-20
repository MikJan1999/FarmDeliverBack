package com.example.demo.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/control")

public class TestControl {
    @GetMapping("/test1")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Próba dostania się na endpoint");
    }



    @GetMapping("/dupa")
    public ResponseEntity<String> getCurrentUser(Authentication authentication) {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println( userDetails.isAccountNonExpired() +"      "+"+Zalogowany użytkownik: " + userDetails.getUsername() + userDetails.getPassword());
            return ResponseEntity.ok(userDetails.getUsername());
        }

        else {
            System.out.println("Użytkownik nie jest zalogowany.");
        }

        return null;
    }

}