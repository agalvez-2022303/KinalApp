package com.albertogalvez.Kinalapp.controller;

import com.albertogalvez.Kinalapp.entity.Producto;
import com.albertogalvez.Kinalapp.service.IProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        model.addAttribute("viewTitle", "Todos los Productos");
        return "productos";
    }

    @GetMapping("/activos")
    public String listarActivos(Model model) {
        model.addAttribute("productos", productoService.listarEstadoProductos());
        model.addAttribute("viewTitle", "Productos Activos");
        return "productos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("viewTitle", "Registrar Nuevo Producto");
        return "formularioProducto";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto, RedirectAttributes flash) {
        try {
            productoService.guardar(producto);
            flash.addFlashAttribute("success", "Producto guardado correctamente.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model, RedirectAttributes flash) {
        // map + orElseGet en lugar de isPresent/get
        return productoService.buscarPorId(id)
                .map(producto -> {
                    model.addAttribute("producto", producto);
                    model.addAttribute("viewTitle", "Editar: " + producto.getNombreProducto());
                    return "formularioProducto";
                })
                .orElseGet(() -> {
                    flash.addFlashAttribute("error", "El producto no existe.");
                    return "redirect:/productos";
                });
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes flash) {
        try {
            productoService.eliminar(id);
            flash.addFlashAttribute("success", "Producto eliminado con éxito.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar: " + e.getMessage());
        }
        return "redirect:/productos";
    }
}