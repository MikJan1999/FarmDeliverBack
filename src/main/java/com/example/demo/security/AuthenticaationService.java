package com.example.demo.security;
import com.example.demo.Repo.UserRepo;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AuthenticaationService {
public final UserRepo userRepo;
public final PasswordEncoder passEnco;
public final JWTService jwtService;
private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Optional<User> isExistUser = userRepo.findByEmail(request.getEmail());
        if (isExistUser.isPresent()) {
            System.out.println(request.getEmail() + "już istnieje");
            return AuthenticationResponse.builder()
                    .build();
        }else{
            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passEnco.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();
            var savedUser =  userRepo.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .email(request.getEmail())
                    .role(request.getRole())
                    .id(user.getId())
                    .build();
        }}
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()

                ));
        var user =userRepo.findByEmail(request.getEmail())
                .orElseThrow();
        String roleuser = String.valueOf(user.getRole());
        Long idUser = user.getId();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(request.getEmail())
                .role(Role.valueOf(roleuser))
                .id(idUser)
                .build();
    }


   }