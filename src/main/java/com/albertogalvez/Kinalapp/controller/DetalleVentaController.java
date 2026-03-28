package com.albertogalvez.Kinalapp.controller;

import com.albertogalvez.Kinalapp.entity.DetalleVenta;
import com.albertogalvez.Kinalapp.service.IDetalleVentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles-venta")
public class DetalleVentaController {

    private final IDetalleVentaService detalleVentaService;

    public DetalleVentaController(IDetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleVenta>> listar() {
        return ResponseEntity.ok(detalleVentaService.listarTodos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<DetalleVenta> buscarPorCodigo(@PathVariable Long codigo) {
        return detalleVentaService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody DetalleVenta detalleVenta) {
        try {
            DetalleVenta nuevo = detalleVentaService.guardar(detalleVenta);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable Long codigo, @RequestBody DetalleVenta detalleVenta) {
        try {
            if (!detalleVentaService.existePorCodigo(codigo)) {
                return ResponseEntity.notFound().build();
            }
            DetalleVenta actualizado = detalleVentaService.actualizar(codigo, detalleVenta);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable Long codigo) {
        try {
            if (!detalleVentaService.existePorCodigo(codigo)) {
                return ResponseEntity.notFound().build();
            }
            detalleVentaService.eliminar(codigo);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/venta/{codigoVenta}")
    public ResponseEntity<List<DetalleVenta>> listarPorVenta(@PathVariable Long codigoVenta) {
        List<DetalleVenta> detalles = detalleVentaService.listarPorVenta(codigoVenta);
        return ResponseEntity.ok(detalles);
    }
}