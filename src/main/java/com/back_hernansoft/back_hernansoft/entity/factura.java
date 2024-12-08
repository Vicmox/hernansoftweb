package com.back_hernansoft.back_hernansoft.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "factura")

public class factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Integer factura;

    @Column(name = "fecha_emision", nullable = false)
    private Date fecha_emision;

    @Column(name = "fecha_vencimiento", nullable = false)
    private Date fecha_vencimiento;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "pedido_id", nullable = false)
    private Integer pedido_id;

    @Column(name = "reacudo_pendiente", nullable = false)
    private double reacudo_pendiente;

    @Column(name = "metodo_pago", nullable = false)
    private String metodo_pago;

    @Column(name = "cuota", nullable = false)
    private Integer cuota;

    @Column(name = "cuota_pendiente", nullable = false)
    private Integer cuota_pendiente;

    @Column(name = "id_cliente", nullable = false)
    private Integer id_cliente;

    @Column(name = "id_vendedor", nullable = false)
    private Integer id_vendedor;

    // @Column(name = "estado", nullable = false)
    // private String estado;
}
