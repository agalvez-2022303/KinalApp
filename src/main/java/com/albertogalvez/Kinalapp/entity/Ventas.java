package com.albertogalvez.Kinalapp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ventas")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_ventas")
    private Long codigoVentas;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total; // Cambiado a BigDecimal

    @Column(name = "estado_venta")
    private int estado;

    @ManyToOne
    @JoinColumn(name = "dpi_clientes", referencedColumnName = "dpi_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "codigo_usuarios", referencedColumnName = "codigo_usuario")
    private Usuario usuario;

    // Constructor vacío
    public Ventas() {}

    // Constructor lleno
    public Ventas(Date fecha, BigDecimal total, int estado, Cliente cliente, Usuario usuario) {
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    // Getters y Setters
    public Long getCodigoVentas() {
        return codigoVentas;
    }

    public void setCodigoVentas(Long codigoVentas) {
        this.codigoVentas = codigoVentas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}