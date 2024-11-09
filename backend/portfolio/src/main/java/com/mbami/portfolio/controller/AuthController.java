package com.mbami.portfolio.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//import com.google.rpc.context.AttributeContext.Response;
import com.mbami.portfolio.dto.AuthRequest;
import com.mbami.portfolio.model.User;
import com.mbami.portfolio.security.jwt.JwtUtil;
import com.mbami.portfolio.service.UserService;

//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(userService.createUser(user));
    }

        @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) {
        if (authRequest.getUsername() == null || authRequest.getPassword() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username and password must not be empty"));
        }
        try {
            User userDetails = userService.loadUserByUsername(authRequest.getUsername());
            String encodedPassword = userDetails.getPassword();
    
            if (passwordEncoder.matches(authRequest.getPassword(), encodedPassword)) {
                String token = jwtUtil.generateToken(userDetails.getUsername());
                return ResponseEntity.ok(Map.of("token", token));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An error occurred"));
        }
    }

    @PostMapping("/refresh")
    public String refreshToken(@RequestParam String token) {
        String username = jwtUtil.extractUsername(token);
        UserDetails userDetails = userService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails.getUsername());
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token, @RequestParam String username) throws Exception {
        UserDetails user = userService.loadUserByUsername(username);

        boolean isValid = jwtUtil.validateToken(token, user);
        return ResponseEntity.ok(isValid);
    }

    /* @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, @RequestHeader("Authorization") String token) {
        // Invalidate the token
        jwtUtil.invalidateToken(token);

        // Invalidate the session if it exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok("Logged out successfully");
    } */
}

