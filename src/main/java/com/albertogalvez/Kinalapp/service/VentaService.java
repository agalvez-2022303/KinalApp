package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Venta;
import com.albertogalvez.Kinalapp.repository.VentaRepository;
import com.albertogalvez.Kinalapp.repository.ClienteRepository;
import com.albertogalvez.Kinalapp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VentaService implements IVentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    public VentaService(VentaRepository ventaRepository,
                        ClienteRepository clienteRepository,
                        UsuarioRepository usuarioRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> listarTodos() {
        return ventaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> buscarPorCodigo(Long codigo) {
        return ventaRepository.findById(codigo);
    }

    @Override
    public Venta guardar(Venta venta) {
        validarVenta(venta);
        // Validar que cliente y usuario existan
        if (!clienteRepository.existsById(venta.getCliente().getDPICliente())) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
        if (!usuarioRepository.existsById(venta.getUsuario().getCodigoUsuario())) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        if (venta.getEstado() == 0) {
            venta.setEstado(1); // Activo por defecto
        }
        return ventaRepository.save(venta);
    }

    @Override
    public Venta actualizar(Long codigo, Venta venta) {
        if (!ventaRepository.existsById(codigo)) {
            throw new RuntimeException("Venta no encontrada con código: " + codigo);
        }
        venta.setCodigoVenta(codigo);
        validarVenta(venta);
        return ventaRepository.save(venta);
    }

    @Override
    public void eliminar(Long codigo) {
        if (!ventaRepository.existsById(codigo)) {
            throw new RuntimeException("Venta no encontrada con código: " + codigo);
        }
        ventaRepository.deleteById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigo(Long codigo) {
        return ventaRepository.existsById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> listarPorEstado(int estado) {
        return ventaRepository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> listarPorCliente(String dpiCliente) {
        return ventaRepository.findByClienteDPICliente(dpiCliente);
    }

    private void validarVenta(Venta venta) {
        if (venta.getFechaVenta() == null) {
            throw new IllegalArgumentException("La fecha de venta es obligatoria");
        }
        if (venta.getTotal() == null) {
            throw new IllegalArgumentException("El total es obligatorio");
        }
        if (venta.getTotal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El total debe ser mayor a cero");
        }
        if (venta.getCliente() == null) {
            throw new IllegalArgumentException("El cliente es obligatorio");
        }
        if (venta.getUsuario() == null) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }
    }
}