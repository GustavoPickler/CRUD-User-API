package com.unisul.br.webAPI.controller;

import com.unisul.br.webAPI.dto.UsuarioDto;
import com.unisul.br.webAPI.exceptions.BusinessException;
import com.unisul.br.webAPI.model.Usuario;
import com.unisul.br.webAPI.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/{value}")
    public ResponseEntity<List<Usuario>> findByValue(@PathVariable("value") String value) {
        return ResponseEntity.ok(usuarioService.findByValue(value));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> create(@RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.create(usuarioDto));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.delete(id));
    }

    @PutMapping
    public ResponseEntity<Usuario> delete(@RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.update(usuarioDto));
    }
}
