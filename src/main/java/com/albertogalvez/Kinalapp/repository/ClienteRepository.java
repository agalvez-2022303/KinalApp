package com.albertogalvez.Kinalapp.repository;

import com.albertogalvez.Kinalapp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,String> {
    List<Cliente> findByEstado(int estado);
}

