package com.unisul.br.webAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Long id;
    private String usuario;
    private String senha;
    private String cpf;
    private Date dataDeNascimento;
    private String estadoCivil;
}
