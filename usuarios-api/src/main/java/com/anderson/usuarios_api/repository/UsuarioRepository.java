package com.anderson.usuarios_api.repository;

import com.anderson.usuarios_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
