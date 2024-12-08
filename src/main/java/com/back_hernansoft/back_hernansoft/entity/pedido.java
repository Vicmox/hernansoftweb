package com.back_hernansoft.back_hernansoft.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Transient;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "pedido")
public class pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column(name = "fecha_pedido", nullable = false)
    private Date fecha_pedido;

    @Column(name = "subtotal", nullable = true)
    private double subtotal;

    @Column(name = "descuento", nullable = true)
    private double descuento;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Column(name = "id_vendedor", nullable = false)
    private Integer idVendedor;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticuloPedido> articulosPedidos = new ArrayList<>();

    // Variables auxiliares para nombres
    @Transient
    private usuario usuarioCliente;

    @Transient
    private usuario usuarioVendedor;

    // Getters y Setters
    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public List<ArticuloPedido> getArticulosPedidos() {
        return articulosPedidos;
    }

    public void setArticulosPedidos(List<ArticuloPedido> articulosPedidos) {
        this.articulosPedidos = articulosPedidos;
    }

    public usuario getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(usuario usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public usuario getUsuarioVendedor() {
        return usuarioVendedor;
    }

    public void setUsuarioVendedor(usuario usuarioVendedor) {
        this.usuarioVendedor = usuarioVendedor;
    }

    @Override
    public String toString() {
        return "pedido{" +
                "idPedido=" + idPedido +
                ", fecha_pedido=" + fecha_pedido +
                ", subtotal=" + subtotal +
                ", descuento=" + descuento +
                ", total=" + total +
                ", estado='" + estado + '\'' +
                ", idCliente=" + idCliente +
                ", idVendedor=" + idVendedor +
                ", articulosPedidos=" + articulosPedidos +
                ", usuarioCliente=" + usuarioCliente +
                ", usuarioVendedor=" + usuarioVendedor +
                '}';
    }

}
