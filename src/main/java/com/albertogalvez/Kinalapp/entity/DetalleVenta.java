package com.albertogalvez.Kinalapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_detalle_venta")
    private int  codigoDetalleVenta;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name =  )




}
