package ru.veqveq.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.veqveq.conference.security.JwtRequest;
import ru.veqveq.conference.security.JwtResponse;
import ru.veqveq.conference.security.JwtTokenUtil;
import ru.veqveq.conference.services.UserService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            e.printStackTrace();
        }
        UserDetails details = userService.loadUserByUsername(request.getUsername());
        String token = jwtTokenUtil.generateToken(details);
        return ResponseEntity.ok(new JwtResponse(token, request.getUsername()));
    }

}
