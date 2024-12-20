package com.project.mindly.service;

import com.project.mindly.config.AuthenticationException;
import com.project.mindly.dtos.userAuth.AuthResponse;
import com.project.mindly.enums.UserRoles;

import com.project.mindly.model.paciente.Paciente;
import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.repository.PacienteRepository;
import com.project.mindly.repository.ProfissionalRepository;
import com.project.mindly.security.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PacienteRepository pacienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(PacienteRepository pacienteRepository, ProfissionalRepository profissionalRepository,
                       PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.pacienteRepository = pacienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public AuthResponse authenticateUser(String email, String senha) throws AuthenticationException {
        Profissional profissional = profissionalRepository.findByEmailProf(email);
        if (profissional != null) {
            if (profissional.getRoles().equals(UserRoles.PROFISSIONAL)) {
                if (passwordEncoder.matches(senha, profissional.getPassword())) {
                    String token = this.tokenService.generateTokenProfissional(profissional);
                    return new AuthResponse(token,UserRoles.PROFISSIONAL);
                }
            }
        }

        Paciente paciente = pacienteRepository.findByEmailPaciente(email);
        if (paciente != null) {
            if (paciente.getRoles().equals(UserRoles.PACIENTE)) {
                if (passwordEncoder.matches(senha, paciente.getPassword())) {
                    String token = this.tokenService.generateTokenPaciente(paciente);
                    return new AuthResponse(token,UserRoles.PACIENTE);
                }
            }
        }

        throw new AuthenticationException("Credenciais inválidas");
    }
}
