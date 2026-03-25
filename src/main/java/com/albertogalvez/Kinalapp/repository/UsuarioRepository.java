package com.albertogalvez.Kinalapp.repository;

import com.albertogalvez.Kinalapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByEstado(int estado);
    Optional<Usuario> findByUsername(String username);
}