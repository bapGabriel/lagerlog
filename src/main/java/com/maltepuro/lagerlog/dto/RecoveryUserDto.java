package com.maltepuro.lagerlog.dto;

import java.util.List;

import com.maltepuro.lagerlog.model.UsuarioRole;

public record RecoveryUserDto(
    Long id,
    String usuario,
    List<UsuarioRole> roles
) {

}
