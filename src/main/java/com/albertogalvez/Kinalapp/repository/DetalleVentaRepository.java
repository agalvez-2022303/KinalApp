package com.albertogalvez.Kinalapp.repository;

import com.albertogalvez.Kinalapp.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> { // Long
    List<DetalleVenta> findByVenta_CodigoVentas(Long codigoVentas);
}