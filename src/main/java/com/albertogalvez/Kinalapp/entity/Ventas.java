package com.albertogalvez.Kinalapp.entity;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "Ventas")
public class Ventas {
    @Id
    @Column(name = "codigo_ventas")
    private int codigoVentas;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "Total")
    private double total;
    @Column(name = "Estado_Venta")
    private int estado;
    @Column(name = "Dpi_Clientes")
    private int dpiClientes;
    @Column
    private int codigoUsuarios;
    //COnstructor Vacío
    public Ventas(){}
    //Constructor lleno
    public Ventas(int codigoVentas, Date fecha, double total, int estado, int dpiClientes, int codigoUsuarios) {
        this.codigoVentas = codigoVentas;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.dpiClientes = dpiClientes;
        this.codigoUsuarios = codigoUsuarios;
    }

    public int getCodigoVentas() {
        return codigoVentas;
    }

    public void setCodigoVentas(int codigoVentas) {
        this.codigoVentas = codigoVentas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getDpiClientes() {
        return dpiClientes;
    }

    public void setDpiClientes(int dpiClientes) {
        this.dpiClientes = dpiClientes;
    }

    public int getCodigoUsuarios() {
        return codigoUsuarios;
    }

    public void setCodigoUsuarios(int codigoUsuarios) {
        this.codigoUsuarios = codigoUsuarios;
    }
}
