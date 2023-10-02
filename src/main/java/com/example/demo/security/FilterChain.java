package com.example.demo.security;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class FilterChain {
   private final JwtFilterAuthentication jwtAuthfilter;
   private final AuthenticationProvider authenticationProvider;
   private final UserDetailsService userDetailsService;

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {

        return new HttpStatusReturningLogoutSuccessHandler();
    }

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/login/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthfilter,
                        UsernamePasswordAuthenticationFilter.class)
                .logout()
               .logoutUrl("/login/logout") // Definiujemy URL wylogowania
               .logoutSuccessHandler(logoutSuccessHandler()) // Ustawiamy obsługę wylogowania
               .invalidateHttpSession(true) // Unieważniamy sesję HTTP
               .deleteCookies("JSESSIONID"); // Usuwamy ciasteczka, jeśli są używan
        return httpSecurity.build();
    }


}