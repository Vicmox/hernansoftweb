package com.back_hernansoft.back_hernansoft.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "articulo_pedido")
public class ArticuloPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_articulo_pedido")
    private Integer idArticuloPedido;

    @ManyToOne
    @JoinColumn(name = "id_articulo", nullable = false)
    private articulo articulo;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private pedido pedido;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "valor_unitario")
    private Double valorUnitario;
}
