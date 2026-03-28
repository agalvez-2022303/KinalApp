package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Ventas;
import java.util.List;
import java.util.Optional;

public interface IVentasService {
    List<Ventas> listarTodos();
    Optional<Ventas> buscarPorId(Long id);
    Ventas guardar(Ventas ventas);
    Ventas actualizar(Long id, Ventas ventas);
    void eliminar(Long id);
    boolean existePorId(Long id);
    List<Ventas> listarPorEstado(int estado);
}