package br.com.tiagodev.retrogamearchive.service;

import br.com.tiagodev.retrogamearchive.domain.dto.AuthRequestDTO;
import br.com.tiagodev.retrogamearchive.domain.dto.AuthResponseDTO;
import br.com.tiagodev.retrogamearchive.domain.dto.RegisterRequestDTO;
import br.com.tiagodev.retrogamearchive.domain.model.User;
import br.com.tiagodev.retrogamearchive.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO dto) {
        // 1. Verifica se email já existe
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // 2. Cria o usuário com senha criptografada
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();

        // 3. Salva no banco
        userRepository.save(user);

        // 4. Gera e retorna o token
        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO login(AuthRequestDTO dto) {
        // 1. Verifica email e senha
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        // 2. Busca o usuário
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3. Gera e retorna o token
        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponseDTO(token);
    }
}
