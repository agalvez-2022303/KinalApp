package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Venta;
import java.util.List;
import java.util.Optional;

public interface IVentaService {
    List<Venta> listarTodos();
    Optional<Venta> buscarPorCodigo(Long codigo);
    Venta guardar(Venta venta);
    Venta actualizar(Long codigo, Venta venta);
    void eliminar(Long codigo);
    boolean existePorCodigo(Long codigo);
    List<Venta> listarPorEstado(int estado);
    List<Venta> listarPorCliente(String dpiCliente);
}