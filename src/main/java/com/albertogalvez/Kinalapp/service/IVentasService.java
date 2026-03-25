package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Ventas;
import java.util.List;
import java.util.Optional;

public interface IVentasService {
    List<Ventas> listarTodos();
    Optional<Ventas> buscarPorId(Integer id);
    Ventas guardar(Ventas ventas);
    Ventas actualizar(Integer id, Ventas ventas);
    void eliminar(Integer id);
    boolean existePorId(Integer id);
    List<Ventas> listarPorEstado(int estado);
}