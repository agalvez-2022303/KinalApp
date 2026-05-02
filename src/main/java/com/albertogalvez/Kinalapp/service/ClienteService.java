package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Cliente;
import com.albertogalvez.Kinalapp.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService implements IClienteServise {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarEstadosActivos() {
        return clienteRepository.findAll()
                .stream()
                .filter(c -> c.getEstado() == 1)
                .toList();
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        validarCliente(cliente);
        if (cliente.getEstado() == 0) cliente.setEstado(1);
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorDPI(String dpi) {
        return clienteRepository.findById(dpi);
    }

    @Override
    public Cliente actualizar(String dpi, Cliente cliente) {
        if (!clienteRepository.existsById(dpi))
            throw new RuntimeException("Cliente no encontrado con DPI: " + dpi);
        cliente.setDPICliente(dpi);
        validarCliente(cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminar(String dpi) {
        if (!clienteRepository.existsById(dpi))
            throw new RuntimeException("El cliente no se encontró con el DPI: " + dpi);
        clienteRepository.deleteById(dpi);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeDPI(String dpi) {
        return clienteRepository.existsById(dpi);
    }

    private void validarCliente(Cliente cliente) {
        String campoFaltante = obtenerCampoFaltante(cliente);
        if (campoFaltante != null)
            throw new IllegalArgumentException("El campo '" + campoFaltante + "' es obligatorio.");
    }

    // Separado en su propio método para que quede más limpio
    private String obtenerCampoFaltante(Cliente cliente) {
        if (cliente.getDPICliente() == null || cliente.getDPICliente().isBlank())     return "DPI";
        if (cliente.getNombreCliente() == null || cliente.getNombreCliente().isBlank()) return "Nombre";
        if (cliente.getApellidoCliente() == null || cliente.getApellidoCliente().isBlank()) return "Apellido";
        return null;
    }
}