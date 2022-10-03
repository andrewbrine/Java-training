package com.matcher.matcher.controller;

import java.util.Objects;

import com.matcher.matcher.config.AuthRequest;
import com.matcher.matcher.config.AuthResponse;
import com.matcher.matcher.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.matcher.matcher.service.JwtUserDetailsService;

import com.matcher.matcher.config.JwtTokenUtil;

import javax.validation.Valid;


@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

//    @Autowired
//    private JwtUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            System.out.println("try");

            String password = passwordEncoder.encode(request.getPassword());
            System.out.println(password);
            boolean match = passwordEncoder.matches(request.getPassword(),password);
            System.out.println(match);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), password)
            );

            Account account = (Account) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateToken(account);
            AuthResponse response = new AuthResponse(account.getUsername(), accessToken);

            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException e) {
            System.out.println("catch" + e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
