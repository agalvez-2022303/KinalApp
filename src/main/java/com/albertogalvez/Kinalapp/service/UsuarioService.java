package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Usuario;
import com.albertogalvez.Kinalapp.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        // Ordenados alfabéticamente por username
        return usuarioRepository.findAll()
                .stream()
                .sorted((a, b) -> a.getUsername().compareToIgnoreCase(b.getUsername()))
                .toList();
    }

    @Override
    public List<Usuario> listarEstadoUsuario() {
        // Solo activos, ordenados por rol primero luego por nombre
        return usuarioRepository.findAll()
                .stream()
                .filter(u -> u.getEstado() == 1)
                .sorted((a, b) -> {
                    int cmpRol = a.getRol().compareToIgnoreCase(b.getRol());
                    return cmpRol != 0 ? cmpRol : a.getUsername().compareToIgnoreCase(b.getUsername());
                })
                .toList();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Switch expression para asignar estado según el rol
        int estadoPorDefecto = switch (usuario.getRol() == null ? "" : usuario.getRol().toUpperCase()) {
            case "ADMIN" -> 1;   // Admin siempre activo
            case "USER"  -> 1;   // User activo por defecto
            default      -> {
                usuario.setRol("USER");
                yield 1;
            }
        };
        if (usuario.getEstado() == 0) usuario.setEstado(estadoPorDefecto);

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario guardarSinEncriptar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorId(int id) {
        return usuarioRepository.findById((long) id);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario actualizar(int id, Usuario usuario) {
        usuario.setCodigoUsuario((long) id);
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(int id) {
        usuarioRepository.deleteById((long) id);
    }

    @Override @Transactional(readOnly = true)
    public boolean existePorId(int id) {
        return usuarioRepository.existsById((long) id);
    }
}