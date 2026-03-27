package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Ventas;
import com.albertogalvez.Kinalapp.repository.VentasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VentasServices implements IVentasService {

    private final VentasRepository ventasRepository;

    public VentasServices(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ventas> listarTodos() {
        return ventasRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ventas> buscarPorId(Long id) {
        return ventasRepository.findById(id);
    }

    @Override
    public Ventas guardar(Ventas ventas) {
        validarVentas(ventas);
        if (ventas.getEstado() == 0) {
            ventas.setEstado(1);
        }
        return ventasRepository.save(ventas);
    }

    @Override
    public Ventas actualizar(Long id, Ventas ventas) {
        if (!ventasRepository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada con ID: " + id);
        }
        ventas.setCodigoVentas(id);
        validarVentas(ventas);
        return ventasRepository.save(ventas);
    }

    @Override
    public void eliminar(Long id) {
        if (!ventasRepository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada con ID: " + id);
        }
        ventasRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorId(Long id) {
        return ventasRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ventas> listarPorEstado(int estado) {
        return ventasRepository.findByEstado(estado);
    }

    private void validarVentas(Ventas ventas) {
        if (ventas.getFecha() == null) {
            throw new IllegalArgumentException("La fecha de venta es obligatoria");
        }
        if (ventas.getTotal() == null || ventas.getTotal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El total debe ser mayor a cero");
        }
        if (ventas.getCliente() == null || ventas.getCliente().getDPICliente() == null) {
            throw new IllegalArgumentException("La venta debe estar asociada a un cliente válido");
        }
        if (ventas.getUsuario() == null || ventas.getUsuario().getCodigoUsuario() == null) {
            throw new IllegalArgumentException("La venta debe estar asociada a un usuario válido");
        }
    }
}