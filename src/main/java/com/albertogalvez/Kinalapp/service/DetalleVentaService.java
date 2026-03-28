package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.DetalleVenta;
import com.albertogalvez.Kinalapp.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleVentaService implements IDetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> listarTodos() {
        return detalleVentaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetalleVenta> buscarPorCodigo(Long codigo) {
        return detalleVentaRepository.findById(codigo);
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalleVenta) {
        validarDetalleVenta(detalleVenta);
        // Calcular subtotal si no viene correcto
        if (detalleVenta.getSubtotal() == null || detalleVenta.getSubtotal().compareTo(BigDecimal.ZERO) == 0) {
            BigDecimal subtotal = detalleVenta.getPrecioUnitario()
                    .multiply(BigDecimal.valueOf(detalleVenta.getCantidad()));
            detalleVenta.setSubtotal(subtotal);
        }
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public DetalleVenta actualizar(Long codigo, DetalleVenta detalleVenta) {
        if (!detalleVentaRepository.existsById(codigo)) {
            throw new RuntimeException("DetalleVenta no encontrado con código: " + codigo);
        }
        detalleVenta.setCodigoDetalleVenta(codigo);
        validarDetalleVenta(detalleVenta);
        // Recalcular subtotal
        BigDecimal subtotal = detalleVenta.getPrecioUnitario()
                .multiply(BigDecimal.valueOf(detalleVenta.getCantidad()));
        detalleVenta.setSubtotal(subtotal);
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public void eliminar(Long codigo) {
        if (!detalleVentaRepository.existsById(codigo)) {
            throw new RuntimeException("DetalleVenta no encontrado con código: " + codigo);
        }
        detalleVentaRepository.deleteById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigo(Long codigo) {
        return detalleVentaRepository.existsById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> listarPorVenta(Long codigoVenta) {
        return detalleVentaRepository.findByVenta_CodigoVenta(codigoVenta);
    }

    private void validarDetalleVenta(DetalleVenta detalleVenta) {
        if (detalleVenta.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        if (detalleVenta.getPrecioUnitario() == null || detalleVenta.getPrecioUnitario().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio unitario debe ser mayor a cero");
        }
        if (detalleVenta.getProducto() == null) {
            throw new IllegalArgumentException("El producto es obligatorio");
        }
        if (detalleVenta.getVenta() == null) {
            throw new IllegalArgumentException("La venta es obligatoria");
        }
    }
}