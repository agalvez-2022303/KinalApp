package com.albertogalvez.Kinalapp.entity;

import jakarta.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ventas")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Asume auto-increment
    @Column(name = "codigo_ventas")
    private Integer codigoVentas;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "total")
    private double total;

    @Column(name = "estado_venta")
    private int estado;

    // Relación con Cliente
    @ManyToOne
    @JoinColumn(name = "dpi_clientes", referencedColumnName = "dpi_cliente")
    private Cliente cliente;


    @ManyToOne
    @JoinColumn(name = "codigo_usuarios", referencedColumnName = "codigo_usuario")
    private Usuario usuario;

    // Constructores
    public Ventas() {}

    public Ventas(Date fecha, double total, int estado, Cliente cliente, Usuario usuario) {
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    // Getters y Setters
    public Integer getCodigoVentas() { return codigoVentas; }
    public void setCodigoVentas(Integer codigoVentas) { this.codigoVentas = codigoVentas; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}