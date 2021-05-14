package com.unisul.br.webAPI.service;

import com.unisul.br.webAPI.dto.UsuarioDto;
import com.unisul.br.webAPI.exceptions.BusinessException;
import com.unisul.br.webAPI.model.Usuario;
import com.unisul.br.webAPI.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ModelMapper modelMapper;

    public UsuarioDto create(UsuarioDto usuarioDto) {
       Usuario usuario = usuarioRepository.findByUsuario(usuarioDto.getUsuario());

        if (usuario != null) {
            throw new BusinessException("Usuario já existe!");
        }

        Usuario cpf = usuarioRepository.findByCpf(usuarioDto.getCpf());

        if (cpf != null) {
            throw new BusinessException("CPF já existe!");
        }

        Usuario response = modelMapper.map(usuarioDto, Usuario.class);
        Usuario usuariosaved = usuarioRepository.save(response);
        usuarioDto.setId(usuariosaved.getId());
        return usuarioDto;
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public String delete(long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new BusinessException("O usuário não existe.");
        }
        usuarioRepository.deleteById(id);
        return "Usuário deletado com sucesso.";
    }

    public List<Usuario> findByValue(String value) {
        return usuarioRepository.findByCpfOrNameFromValue(value);
    }

    public Usuario update(UsuarioDto usuarioDto) {
        return usuarioRepository.saveAndFlush(modelMapper.map(usuarioDto, Usuario.class));
    }
}
