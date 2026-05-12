package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Producto;
import com.albertogalvez.Kinalapp.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService implements IProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> listarEstadoProductos() {
        return productoRepository.findAll()
                .stream()
                .filter(p -> p.getEstado() == 1)
                .toList();
    }

    @Override
    public List<String> listarStock() {
        return productoRepository.findAll()
                .stream()
                .map(p -> p.getNombreProducto() + " | Stock: " + p.getStock()
                        + " [" + nivelStock(p.getStock()) + "]")
                .toList();
    }

    // Switch clásico sobre int - válido en cualquier versión de Java
    private String nivelStock(int stock) {
        if (stock <= 5)  return "⚠ BAJO";
        if (stock <= 20) return "✓ NORMAL";
        return "↑ ALTO";
    }

    @Override
    public Producto guardar(Producto producto) {
        validarProducto(producto);
        if (producto.getEstado() == 0) producto.setEstado(1);
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarPorId(int id) {
        return productoRepository.findById((long) id);
    }

    @Override
    public Producto actualizar(int id, Producto producto) {
        if (!productoRepository.existsById((long) id))
            throw new RuntimeException("No existe el producto con el id: " + id);
        producto.setCodigoProducto((long) id);
        validarProducto(producto);
        return productoRepository.save(producto);
    }

    @Override
    public void eliminar(int id) {
        if (!productoRepository.existsById((long) id))
            throw new RuntimeException("No existe el producto con el id: " + id);
        productoRepository.deleteById((long) id);
    }

    @Override
    public boolean existePorId(int id) {
        return productoRepository.existsById((long) id);
    }

    private void validarProducto(Producto producto) {
        String error = obtenerErrorValidacion(producto);
        if (error != null) throw new IllegalArgumentException(error);
    }

    private String obtenerErrorValidacion(Producto producto) {
        if (producto == null)                                                        return "El producto no puede ser nulo.";
        if (producto.getNombreProducto() == null || producto.getNombreProducto().isBlank()) return "El nombre del producto es obligatorio.";
        if (producto.getPrecio() == null)                                            return "El precio del producto es obligatorio.";
        if (producto.getStock() < 0)                                                 return "El stock no puede ser negativo.";
        return null;
    }
}