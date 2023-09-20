package com.example.demo.security;

import com.example.demo.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




@Configuration
@RequiredArgsConstructor
public class Config {
    private final UserRepo repo;

    @Bean
    public UserDetailsService userDetailsService(){
    return username -> repo.findByEmail(username)
            .orElseThrow(()-> new UsernameNotFoundException("Nie znaleziono użytkownika"));
}
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
}
@Bean
    public AuthenticationManager autheManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
}
}