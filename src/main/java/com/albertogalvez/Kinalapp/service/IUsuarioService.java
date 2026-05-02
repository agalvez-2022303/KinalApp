package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> listarTodos();
    List<Usuario> listarEstadoUsuario();
    Usuario guardar(Usuario usuario);           // encripta password
    Usuario guardarSinEncriptar(Usuario usuario); // ya viene encriptado
    Optional<Usuario> buscarPorId(int id);
    Optional<Usuario> buscarPorEmail(String email);
    Usuario actualizar(int id, Usuario usuario);
    void eliminar(int id);
    boolean existePorId(int id);
}