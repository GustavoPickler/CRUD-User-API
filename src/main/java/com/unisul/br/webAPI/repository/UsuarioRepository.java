package com.unisul.br.webAPI.repository;

import com.unisul.br.webAPI.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsuario(String usuario);
    Usuario findByCpf(String cpf);

    @Query(value = "SELECT u FROM usuario u WHERE LOWER(u.usuario) LIKE LOWER(concat('%', :value,'%')) OR u.cpf LIKE LOWER(concat('%', :value, '%'))")
    List<Usuario> findByCpfOrNameFromValue(@Param("value") String value);
}