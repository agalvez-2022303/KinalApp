package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Venta;

import java.util.List;
import java.util.Optional;

public interface IVentaService {

    List<Venta> listarTodos();

    List<Venta> listarEstadoVenta();

    Venta guardar (Venta venta);

    Optional<Venta> buscarPorId(int id);

    Venta actualizar (int id, Venta venta);

    boolean existePorId(int id);
}