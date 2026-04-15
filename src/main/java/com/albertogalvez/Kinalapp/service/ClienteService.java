package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Cliente;
import com.albertogalvez.Kinalapp.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarEstado(int estado) {
        return clienteRepository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorDPI(String dpi) {
        return clienteRepository.existsById(dpi);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        validarCliente(cliente);
        if (cliente.getEstado() == 0) {
            cliente.setEstado(1);
        }
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> bucarPorDPI(String dpi) {
        return clienteRepository.findById(dpi);
    }

    @Override
    public Cliente actualizar(String dpi, Cliente clienteActualizado) {
        Cliente existente = clienteRepository.findById(dpi)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con DPI: " + dpi));
        existente.setNombreCliente(clienteActualizado.getNombreCliente());
        existente.setApellidoCliente(clienteActualizado.getApellidoCliente());
        existente.setDireccion(clienteActualizado.getDireccion());
        validarCliente(existente);
        return clienteRepository.save(existente);
    }

    @Override
    public void eliminar(String dpi) {
        if (!clienteRepository.existsById(dpi)) {
            throw new RuntimeException("Cliente no encontrado con el DPI: " + dpi);
        }
        clienteRepository.deleteById(dpi);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeDPI(String dpi) {
        return clienteRepository.existsById(dpi);
    }

    private void validarCliente(Cliente cliente) {
        if (cliente.getDpiCliente() == null || cliente.getDpiCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El DPI es un dato obligatorio");
        }
        if (cliente.getNombreCliente() == null || cliente.getNombreCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es un dato obligatorio");
        }
        if (cliente.getApellidoCliente() == null || cliente.getApellidoCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es un dato obligatorio");
        }
    }
}