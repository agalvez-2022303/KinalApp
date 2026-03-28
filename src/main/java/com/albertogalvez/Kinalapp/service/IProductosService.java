package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Productos;
import java.util.List;
import java.util.Optional;

public interface IProductosService {
    List<Productos> listarTodos();
    Optional<Productos> buscarPorId(Long id);
    Productos guardar(Productos producto);
    Productos actualizar(Long id, Productos producto);
    void eliminar(Long id);
    boolean existePorId(Long id);
    List<Productos> listarPorEstado(int estado);
}