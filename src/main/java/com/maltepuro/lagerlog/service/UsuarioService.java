package com.maltepuro.lagerlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.maltepuro.lagerlog.config.SecurityConfiguration;
import com.maltepuro.lagerlog.dto.CreateUsuarioDto;
import com.maltepuro.lagerlog.dto.LoginUsuarioDto;
import com.maltepuro.lagerlog.dto.RecoveryJwtTokenDto;
import com.maltepuro.lagerlog.model.Usuario;
import com.maltepuro.lagerlog.model.UsuarioRole;
import com.maltepuro.lagerlog.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public RecoveryJwtTokenDto authenticateUser(LoginUsuarioDto loginUsuarioDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUsuarioDto.usuario(), loginUsuarioDto.senha());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    public void createUser(CreateUsuarioDto createUsuarioDto){
        Usuario novoUsuario = Usuario.builder()
            .usuario(createUsuarioDto.usuario())
            .senha(securityConfiguration.passwordEncoder().encode(createUsuarioDto.senha()))
            .roles(List.of(UsuarioRole.builder().name(createUsuarioDto.role()).build()))
            .build();
        
        usuarioRepository.save(novoUsuario);
    }
}
