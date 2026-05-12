package com.albertogalvez.Kinalapp.repository;

import com.albertogalvez.Kinalapp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClienteRepository extends JpaRepository<Cliente,String> {

}