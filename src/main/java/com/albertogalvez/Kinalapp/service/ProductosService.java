package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Productos;
import com.albertogalvez.Kinalapp.repository.ProductosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductosService implements IProductosService {

    private final ProductosRepository productosRepository;

    public ProductosService(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Productos> listarTodos() {
        return productosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Productos> buscarPorId(Long id) {
        return productosRepository.findById(id);
    }

    @Override
    public Productos guardar(Productos producto) {
        validarProducto(producto);
        if (producto.getEstado() == 0) {
            producto.setEstado(1);
        }
        return productosRepository.save(producto);
    }

    @Override
    public Productos actualizar(Long id, Productos producto) {
        if (!productosRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        producto.setCodigo_producto(id);
        validarProducto(producto);
        return productosRepository.save(producto);
    }

    @Override
    public void eliminar(Long id) {
        if (!productosRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        productosRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorId(Long id) {
        return productosRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Productos> listarPorEstado(int estado) {
        return productosRepository.findByEstado(estado);
    }

    private void validarProducto(Productos producto) {
        if (producto.getNombre_producto() == null || producto.getNombre_producto().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }
}