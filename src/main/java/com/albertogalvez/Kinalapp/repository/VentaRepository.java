package com.albertogalvez.Kinalapp.repository;

import com.albertogalvez.Kinalapp.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByEstado(int estado);
    List<Venta> findByClienteDPICliente(String dpiCliente);
}