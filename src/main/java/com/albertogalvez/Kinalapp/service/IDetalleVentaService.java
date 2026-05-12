package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.DetalleVenta;

import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {

    List<DetalleVenta> listarVentas();

    List<DetalleVenta> listarEstado();

    DetalleVenta guardar (DetalleVenta detalleVenta);

    Optional<DetalleVenta> buscarPorId (int id);

    boolean existePorId (int id);
}