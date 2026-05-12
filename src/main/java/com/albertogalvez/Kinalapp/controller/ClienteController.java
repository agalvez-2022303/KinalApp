package com.albertogalvez.Kinalapp.controller;

import com.albertogalvez.Kinalapp.entity.Cliente;
import com.albertogalvez.Kinalapp.service.IClienteServise;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteServise clienteService;

    public ClienteController(IClienteServise clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("viewTitle", "Todos los Clientes");
        return "clientes";
    }

    @GetMapping("/estado")
    public String listarActivos(Model model) {
        model.addAttribute("clientes", clienteService.listarEstadosActivos());
        model.addAttribute("viewTitle", "Clientes Activos");
        return "clientes";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam("dpi") String dpi, Model model) {
        // ifPresentOrElse en lugar de if/else
        clienteService.buscarPorDPI(dpi).ifPresentOrElse(
                cliente -> {
                    model.addAttribute("clientes", List.of(cliente));
                    model.addAttribute("viewTitle", "Resultado de Búsqueda");
                },
                () -> {
                    model.addAttribute("clientes", clienteService.listarTodos());
                    model.addAttribute("error", "No se encontró el cliente con DPI: " + dpi);
                    model.addAttribute("viewTitle", "Todos los Clientes");
                }
        );
        return "clientes";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("viewTitle", "Registrar Nuevo Cliente");
        return "formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente, RedirectAttributes flash) {
        try {
            clienteService.guardar(cliente);
            flash.addFlashAttribute("success", "Cliente guardado correctamente.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{dpi}")
    public String mostrarFormularioEditar(@PathVariable String dpi, Model model, RedirectAttributes flash) {
        // map + orElseGet en lugar de isPresent/get
        return clienteService.buscarPorDPI(dpi)
                .map(cliente -> {
                    model.addAttribute("cliente", cliente);
                    model.addAttribute("viewTitle", "Editar: " + cliente.getNombreCliente());
                    return "formulario";
                })
                .orElseGet(() -> {
                    flash.addFlashAttribute("error", "El cliente no existe.");
                    return "redirect:/clientes";
                });
    }

    @GetMapping("/eliminar/{dpi}")
    public String eliminar(@PathVariable String dpi, RedirectAttributes flash) {
        try {
            clienteService.eliminar(dpi);
            flash.addFlashAttribute("success", "Cliente eliminado con éxito.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar: " + e.getMessage());
        }
        return "redirect:/clientes";
    }
}