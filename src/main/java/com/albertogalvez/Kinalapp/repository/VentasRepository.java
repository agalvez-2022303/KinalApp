package com.albertogalvez.Kinalapp.repository;

import com.albertogalvez.Kinalapp.entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VentasRepository extends JpaRepository<Ventas, Integer> {
    List<Ventas> findByEstado(int estado);
    // Puedes agregar otros métodos de búsqueda, por ejemplo por cliente
    List<Ventas> findByCliente_DPICliente(String dpi);
}