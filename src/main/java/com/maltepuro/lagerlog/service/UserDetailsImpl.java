package com.maltepuro.lagerlog.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.maltepuro.lagerlog.model.Usuario;

import lombok.Getter;

@Getter
public class UserDetailsImpl implements UserDetails{
    private Usuario usuario;

    public UserDetailsImpl(Usuario usuario){
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*
         Este método converte a lista de papéis (roles) associados ao usuário 
         em uma coleção de GrantedAuthorities, que é a forma que o Spring Security 
         usa para representar papéis. Isso é feito mapeando cada papel para um 
         novo SimpleGrantedAuthority, que é uma implementação simples de 
         GrantedAuthority
        */
        return usuario.getRoles()
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().name()))
            .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        //Retorna a credencial do usuário
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
