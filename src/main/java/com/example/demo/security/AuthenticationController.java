package com.example.demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

private final AuthenticaationService authenticaationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse>register
            (@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authenticaationService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {
return ResponseEntity
        .ok(authenticaationService.authenticate(request));
    }
@GetMapping("/TEST")
    public String get(){
        return "DWDSDS";
}




}