package com.albertogalvez.Kinalapp.controller;

import com.albertogalvez.Kinalapp.entity.Ventas;
import com.albertogalvez.Kinalapp.service.IVentasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentasController {

    private final IVentasService ventasService;

    public VentasController(IVentasService ventasService) {
        this.ventasService = ventasService;
    }

    @GetMapping
    public ResponseEntity<List<Ventas>> listar() {
        return ResponseEntity.ok(ventasService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ventas> buscarPorId(@PathVariable Long id) {
        return ventasService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Ventas ventas) {
        try {
            Ventas nuevaVenta = ventasService.guardar(ventas);
            return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Ventas ventas) {
        try {
            if (!ventasService.existePorId(id)) {
                return ResponseEntity.notFound().build();
            }
            Ventas ventaActualizada = ventasService.actualizar(id, ventas);
            return ResponseEntity.ok(ventaActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            if (!ventasService.existePorId(id)) {
                return ResponseEntity.notFound().build();
            }
            ventasService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Ventas>> listarPorEstado(@PathVariable int estado) {
        return ResponseEntity.ok(ventasService.listarPorEstado(estado));
    }
}