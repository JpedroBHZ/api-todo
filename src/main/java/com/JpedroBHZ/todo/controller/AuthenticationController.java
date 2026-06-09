package com.JpedroBHZ.todo.controller;

import com.JpedroBHZ.todo.dto.AuthenticationDTO;
import com.JpedroBHZ.todo.dto.LoginResponseDTO;
import com.JpedroBHZ.todo.infra.security.TokenService;
import com.JpedroBHZ.todo.model.User;
import com.JpedroBHZ.todo.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        User user = this.userRepository.findByLogin(data.login()).orElse(null);

        if (user != null && passwordEncoder.matches(data.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(token));
        }

        return ResponseEntity.status(401).build(); // Retorna Unauthorized se errar senha ou usuário
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid AuthenticationDTO data) {
        if (this.userRepository.findByLogin(data.login()).isPresent()) {
            return ResponseEntity.badRequest().build(); // Usuário já existe
        }

        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = new User(null, data.login(), encryptedPassword);

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}