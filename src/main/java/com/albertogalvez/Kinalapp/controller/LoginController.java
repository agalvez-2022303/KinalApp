package com.albertogalvez.Kinalapp.controller;

import com.albertogalvez.Kinalapp.entity.Usuario;
import com.albertogalvez.Kinalapp.service.IUsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final IUsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public LoginController(IUsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    // Spring Security procesa el POST /login automáticamente.
    // Solo necesitamos mostrar la vista.
    @GetMapping("/login")
    public String mostrarLogin(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout,
            Model model) {
        if (error != null) model.addAttribute("error", "Correo o contraseña incorrectos.");
        if (logout != null) model.addAttribute("success", "Sesión cerrada correctamente.");
        return "login";
    }

    @GetMapping("/register")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/register")
    public String procesarRegistro(@RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String nombre,
                                   RedirectAttributes flash) {
        if (usuarioService.buscarPorEmail(email).isPresent()) {
            flash.addFlashAttribute("error", "Ya existe una cuenta con ese correo.");
            return "redirect:/register";
        }
        Usuario nuevo = new Usuario();
        nuevo.setUsername(nombre);
        nuevo.setEmail(email);
        nuevo.setPassword(passwordEncoder.encode(password)); // BCrypt
        nuevo.setRol("USER");
        nuevo.setEstado(1);
        usuarioService.guardarSinEncriptar(nuevo);
        flash.addFlashAttribute("success", "Registro exitoso. Ahora puedes iniciar sesión.");
        return "redirect:/login";
    }

    @GetMapping("/acceso-denegado")
    public String accesoDenegado() {
        return "acceso-denegado";
    }
}