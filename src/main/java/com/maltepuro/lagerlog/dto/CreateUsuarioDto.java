package com.maltepuro.lagerlog.dto;

import com.maltepuro.lagerlog.model.RoleName;

public record CreateUsuarioDto(
    String usuario,
    String senha,
    RoleName role
) {
    
}
