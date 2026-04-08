package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> listarTodos();
    Optional<Usuario> buscarPorCodigo(Long codigo);
    Usuario guardar(Usuario usuario);
    Usuario actualizar(Long codigo, Usuario usuario);
    void eliminar(Long codigo);
    boolean existePorCodigo(Long codigo);
    List<Usuario> listarPorEstado(int estado);
    Optional<Usuario> buscarPorUsername(String username);
    boolean existeUsername(String username);
}