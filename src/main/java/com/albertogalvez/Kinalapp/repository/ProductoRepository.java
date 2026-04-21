package com.albertogalvez.Kinalapp.repository;

import com.albertogalvez.Kinalapp.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}