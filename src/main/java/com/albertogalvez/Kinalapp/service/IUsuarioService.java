package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> listarTodos();
    Optional<Usuario> buscarPorId(Long id);
    Usuario guardar(Usuario usuario);
    Usuario actualizar(Long id, Usuario usuario);
    void eliminar(Long id);
    boolean existePorId(Long id);
    List<Usuario> listarPorEstado(int estado);
}