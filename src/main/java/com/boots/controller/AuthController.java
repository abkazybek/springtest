package com.boots.controller;

import com.boots.config.jwt.JwtUtils;
import com.boots.config.jwt.MessageResponse;
import com.boots.entity.Jwt;
import com.boots.entity.Role;
import com.boots.entity.User;
import com.boots.repository.RoleRepository;
import com.boots.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRespository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody User user) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new Jwt(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        if (userRespository.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }

        User user1 = new User(user.getUsername(),
                passwordEncoder.encode(user.getPassword()));

        Set<Role> reqRoles = user.getRoles();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            Role userRole = roleRepository
                    .findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                if ("ROLE_ADMIN".equals(r)) {
                    Role adminRole = roleRepository
                            .findByName("ROLE_USER")
                            .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                    roles.add(adminRole);
                }
            });
        }
        user.setRoles(roles);
        userRespository.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}

