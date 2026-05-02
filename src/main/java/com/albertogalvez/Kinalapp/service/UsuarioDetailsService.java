package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Usuario;
import com.albertogalvez.Kinalapp.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        if (usuario.getEstado() != 1) {
            throw new UsernameNotFoundException("Usuario inactivo");
        }

        // El rol en BD es "ADMIN" o "USER" -> Spring Security necesita "ROLE_ADMIN" / "ROLE_USER"
        String rol = "ROLE_" + usuario.getRol().toUpperCase();

        return new User(usuario.getEmail(), usuario.getPassword(),
                List.of(new SimpleGrantedAuthority(rol)));
    }
}