package com.lattmat.devop.controller;

import com.lattmat.devop.dto.UserDto;
import com.lattmat.devop.service.UserAuthenticationService;
import com.lattmat.devop.utility.JWTUtility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JWTUtility jwtUtility;
    private final UserAuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserAuthenticationService authenticationService, JWTUtility jwtUtility) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.jwtUtility = jwtUtility;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@RequestBody UserDto userDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getName(), userDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = authenticationService.loadUserByUsername(userDto.getName());
            String token = jwtUtility.generateToken(userDetails);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        try {
            authenticationService.registerUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed.");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refreshToken(@RequestBody String expiredToken) {
        try {
            String username = jwtUtility.extractUsername(expiredToken);
            UserDetails userDetails = authenticationService.loadUserByUsername(username);

            if (Boolean.TRUE.equals(jwtUtility.validateToken(expiredToken, userDetails))) {
                String refreshedToken = jwtUtility.generateToken(userDetails);
                Map<String, String> response = new HashMap<>();
                response.put("token", refreshedToken);
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid or expired token.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error: " + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully.");
    }

    @GetMapping("/me")
    public ResponseEntity<Object> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated.");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDto userDto = new UserDto();
        userDto.setName(userDetails.getUsername());
        // Add additional user details if needed
        return ResponseEntity.ok(userDto);
    }
}