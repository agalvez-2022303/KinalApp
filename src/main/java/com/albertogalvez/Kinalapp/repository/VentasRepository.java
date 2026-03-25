package com.albertogalvez.Kinalapp.repository;

import com.albertogalvez.Kinalapp.entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VentasRepository extends JpaRepository<Ventas, Integer> {
    //Buscar por estado
    List<Ventas> findByEstado(int estado);
    //Buscar por DPI
    List<Ventas> findByCliente_DPICliente(String dpi);
}