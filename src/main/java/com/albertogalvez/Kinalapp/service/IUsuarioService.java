package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> listarTodos();
    Optional<Usuario> buscarPorId(Integer id);
    Usuario guardar(Usuario usuario);
    Usuario actualizar(Integer id, Usuario usuario);
    void eliminar(Integer id);
    boolean existePorId(Integer id);
    List<Usuario> listarPorEstado(int estado);
}