package com.tsyhankov.tasktracker.controller;

import com.tsyhankov.tasktracker.controller.dto.AuthenticationRequestDto;
import com.tsyhankov.tasktracker.controller.dto.AuthenticationResponseDto;
import com.tsyhankov.tasktracker.entity.User;
import com.tsyhankov.tasktracker.service.AuthenticationService;
import com.tsyhankov.tasktracker.service.CustomUserDetailsService;
import com.tsyhankov.tasktracker.service.UserService;
import com.tsyhankov.tasktracker.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody AuthenticationRequestDto dto) throws Exception {
        User user = authenticationService.register(dto.getEmail(),
                dto.getPassword(), dto.getFirstName(), dto.getLastName());
        return user;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody AuthenticationRequestDto authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                            authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponseDto(jwt));
    }
}
