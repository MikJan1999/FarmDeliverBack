package com.example.demo.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
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

    @GetMapping("/get-session")
    public ResponseEntity<String> getSessionData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        return ResponseEntity.ok("Wartość z sesji: " + username);
    }
    @GetMapping("/set-session")
    public ResponseEntity<String> setSessionData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("username", "john_doe");
        return ResponseEntity.ok("Dane sesji zostały ustawione.");
    }
}