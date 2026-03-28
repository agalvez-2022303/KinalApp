package com.albertogalvez.Kinalapp.controller;

import com.albertogalvez.Kinalapp.entity.Productos;
import com.albertogalvez.Kinalapp.service.IProductosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    private final IProductosService productosService;

    public ProductosController(IProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping
    public ResponseEntity<List<Productos>> listar() {
        return ResponseEntity.ok(productosService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Productos> buscarPorId(@PathVariable Long id) {
        return productosService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Productos producto) {
        try {
            Productos nuevo = productosService.guardar(producto);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Productos producto) {
        try {
            if (!productosService.existePorId(id)) {
                return ResponseEntity.notFound().build();
            }
            Productos actualizado = productosService.actualizar(id, producto);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            if (!productosService.existePorId(id)) {
                return ResponseEntity.notFound().build();
            }
            productosService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Productos>> listarPorEstado(@PathVariable int estado) {
        return ResponseEntity.ok(productosService.listarPorEstado(estado));
    }
}