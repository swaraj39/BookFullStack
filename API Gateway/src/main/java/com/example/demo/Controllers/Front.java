package com.example.demo.Controllers;


import com.example.demo.Config.JwtUtil;
import com.example.demo.Model.Admin;
import com.example.demo.Repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class Front {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Admin a){
        a.setPassword(passwordEncoder.encode(a.getPassword()));
        adminRepo.save(a);
        return ResponseEntity.ok("Saved");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> user) {

        String username = user.get("username");
        String password = user.get("password");

        // 1️⃣ Fetch from DB
        Admin admin = adminRepo.findById(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2️⃣ Compare encoded password
        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 3️⃣ Generate JWT
        String token = jwtUtil.generateToken(username);

        return ResponseEntity.ok(Map.of("token", token));
    }
}

