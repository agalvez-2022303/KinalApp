package com.albertogalvez.Kinalapp.controller;

import com.albertogalvez.Kinalapp.entity.Usuario;
import com.albertogalvez.Kinalapp.service.IUsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("viewTitle", "Todos los Usuarios");
        return "usuarios";
    }

    @GetMapping("/activos")
    public String listarActivos(Model model) {
        model.addAttribute("usuarios", usuarioService.listarEstadoUsuario());
        model.addAttribute("viewTitle", "Usuarios Activos");
        return "usuarios";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam(required = false) Integer id,
                              Model model, RedirectAttributes flash) {
        if (id == null) {
            flash.addFlashAttribute("error", "Debe ingresar un ID para buscar.");
            return "redirect:/usuarios";
        }

        // ifPresentOrElse en lugar de if/else sobre el Optional
        usuarioService.buscarPorId(id).ifPresentOrElse(
                usuario -> {
                    model.addAttribute("usuarios", List.of(usuario));
                    model.addAttribute("viewTitle", "Resultado: ID " + id);
                },
                () -> {
                    model.addAttribute("usuarios", usuarioService.listarTodos());
                    model.addAttribute("error", "No se encontró el usuario con ID: " + id);
                    model.addAttribute("viewTitle", "Todos los Usuarios");
                }
        );
        return "usuarios";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("viewTitle", "Registrar Nuevo Usuario");
        return "formularioUsuario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario, RedirectAttributes flash) {
        try {
            usuarioService.guardar(usuario);
            flash.addFlashAttribute("success", "Usuario guardado correctamente.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model, RedirectAttributes flash) {
        // map + orElse en lugar de isPresent/get
        return usuarioService.buscarPorId(id)
                .map(usuario -> {
                    model.addAttribute("usuario", usuario);
                    model.addAttribute("viewTitle", "Editar: " + usuario.getUsername());
                    return "formularioUsuario";
                })
                .orElseGet(() -> {
                    flash.addFlashAttribute("error", "El usuario no existe.");
                    return "redirect:/usuarios";
                });
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes flash) {
        try {
            usuarioService.eliminar(id);
            flash.addFlashAttribute("success", "Usuario eliminado con éxito.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar: " + e.getMessage());
        }
        return "redirect:/usuarios";
    }
}