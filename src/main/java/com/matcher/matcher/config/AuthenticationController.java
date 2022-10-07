package com.matcher.matcher.config;

import java.util.Optional;

import com.matcher.matcher.config.AuthRequest;
import com.matcher.matcher.config.AuthResponse;
import com.matcher.matcher.entity.account.Account;
import com.matcher.matcher.entity.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.matcher.matcher.config.JwtTokenUtil;

import javax.validation.Valid;


@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    AccountRepository repository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Account accountMatch = repository.findByUsername((request.getUsername())).orElse(new Account("",""));

            boolean match = passwordEncoder.matches(request.getPassword(),accountMatch.getPassword());

            Account account = new Account (request.getUsername(), request.getPassword());
            String accessToken = jwtTokenUtil.generateToken(account);
            AuthResponse response = new AuthResponse(account.getUsername(), accessToken);

            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
