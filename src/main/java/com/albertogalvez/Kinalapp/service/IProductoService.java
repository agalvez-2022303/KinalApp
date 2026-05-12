package com.albertogalvez.Kinalapp.service;

import com.albertogalvez.Kinalapp.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<Producto> listarTodos();

    List<Producto> listarEstadoProductos();

    List<String> listarStock();

    Producto guardar(Producto producto);

    Optional<Producto> buscarPorId(int id);

    Producto actualizar(int id, Producto producto);

    void eliminar(int id);

    boolean existePorId(int id);

}