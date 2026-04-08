package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.DetalleVenta;
import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {
    List<DetalleVenta> listarTodos();
    Optional<DetalleVenta> buscarPorCodigo(Long codigo);
    DetalleVenta guardar(DetalleVenta detalleVenta);
    DetalleVenta actualizar(Long codigo, DetalleVenta detalleVenta);
    void eliminar(Long codigo);
    boolean existePorCodigo(Long codigo);
    List<DetalleVenta> listarPorVenta(Long codigoVenta);
}