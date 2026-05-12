package com.albertogalvez.Kinalapp.controller;

import com.albertogalvez.Kinalapp.service.IClienteServise;
import com.albertogalvez.Kinalapp.service.IProductoService;
import com.albertogalvez.Kinalapp.service.IVentaService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final IClienteServise clienteService;
    private final IProductoService productoService;
    private final IVentaService ventaService;

    public DashboardController(IClienteServise clienteService,
                               IProductoService productoService,
                               IVentaService ventaService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.ventaService = ventaService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        var todosClientes   = clienteService.listarTodos();
        var clientesActivos = clienteService.listarEstadosActivos();
        var productos       = productoService.listarTodos();
        var ventas          = ventaService.listarTodos();

        model.addAttribute("totalClientes",   todosClientes.size());
        model.addAttribute("clientesActivos", clientesActivos.size());
        model.addAttribute("totalProductos",  productos.size());
        model.addAttribute("totalVentas",     ventas.size());
        model.addAttribute("ultimosClientes", todosClientes.stream().limit(5).toList());
        model.addAttribute("nombreUsuario",   authentication.getName());

        return "dashboard";
    }
}