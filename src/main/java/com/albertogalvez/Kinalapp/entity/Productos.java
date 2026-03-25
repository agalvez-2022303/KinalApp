package com.albertogalvez.Kinalapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto")
    private int codigo_producto;

    @Column(name = "nombre_producto", nullable = false, length = 50)
    private String nombre_producto;

    @Column(name = "Precio", precision = 10, scale = 2)
    private double precio;

    @Column(name = "Stock")
    private int stock;

    @Column(name = "Estado")
    private int estado;

    // @ManyToOne
    //  @JoinColumn(name = "codigoDetalleVenta", referencedColumnName = "codigoDetalleVenta")
    //private  CodigoDetalleVenta codigoDetalleVenta;

    //Constructor Vacío
    public Productos() {
    }

    //Constructor lleno
    public Productos(String nombre_producto, double precio, int stock, int estado) {
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
    }
    //Getters y Setters

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(int codigo_producto) {
        this.codigo_producto = codigo_producto;
    }



}