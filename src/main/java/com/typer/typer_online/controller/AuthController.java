package com.typer.typer_online.controller;


import com.typer.typer_online.model.AuthenticationResponse;
import com.typer.typer_online.model.Credentials;
import com.typer.typer_online.model.Tip;
import com.typer.typer_online.model.UsernameFreeResponse;
import com.typer.typer_online.service.MyUserDetailService;
import com.typer.typer_online.service.RegisterService;
import com.typer.typer_online.service.TipService;
import com.typer.typer_online.util.JwtUtil;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600 )
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private RegisterService registerService;

    @PostMapping(value = "/isUsernameFree")
    public ResponseEntity<?> isUsernameFree(@RequestBody String username){
        return ResponseEntity.ok(new UsernameFreeResponse(this.registerService.isUsernameFree(username)));
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<?> register(@Valid @RequestBody Credentials credentials){
        if(this.registerService.isUsernameFree(credentials.getUsername())) {
            this.registerService.register(credentials);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.ok(new UsernameFreeResponse(false));
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody Credentials credentials) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailService
                .loadUserByUsername(credentials.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
