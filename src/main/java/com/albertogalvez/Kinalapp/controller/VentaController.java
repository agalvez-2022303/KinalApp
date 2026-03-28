package com.albertogalvez.Kinalapp.controller;

import com.albertogalvez.Kinalapp.entity.Venta;
import com.albertogalvez.Kinalapp.service.IVentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final IVentaService ventaService;

    public VentaController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listar() {
        return ResponseEntity.ok(ventaService.listarTodos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Venta> buscarPorCodigo(@PathVariable Long codigo) {
        return ventaService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Venta venta) {
        try {
            Venta nueva = ventaService.guardar(venta);
            return new ResponseEntity<>(nueva, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable Long codigo, @RequestBody Venta venta) {
        try {
            if (!ventaService.existePorCodigo(codigo)) {
                return ResponseEntity.notFound().build();
            }
            Venta actualizada = ventaService.actualizar(codigo, venta);
            return ResponseEntity.ok(actualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable Long codigo) {
        try {
            if (!ventaService.existePorCodigo(codigo)) {
                return ResponseEntity.notFound().build();
            }
            ventaService.eliminar(codigo);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Venta>> listarPorEstado(@PathVariable int estado) {
        List<Venta> ventas = ventaService.listarPorEstado(estado);
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/cliente/{dpiCliente}")
    public ResponseEntity<List<Venta>> listarPorCliente(@PathVariable String dpiCliente) {
        List<Venta> ventas = ventaService.listarPorCliente(dpiCliente);
        return ResponseEntity.ok(ventas);
    }
}