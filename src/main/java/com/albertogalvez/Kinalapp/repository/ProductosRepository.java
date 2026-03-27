package com.albertogalvez.Kinalapp.repository;

import com.albertogalvez.Kinalapp.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductosRepository extends JpaRepository<Productos, Long> { // Long
    List<Productos> findByEstado(int estado);
}