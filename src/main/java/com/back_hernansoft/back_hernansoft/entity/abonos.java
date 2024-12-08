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
@Table(name = "abonos")
public class abonos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_abono")
    private Integer idAbono;

    @Column(name = "id_factura", nullable = false)
    private Integer id_factura;
    
    @Column(name = "fecha_pago", nullable = false)
    private Date fecha_pago;

    @Column(name = "fecha_limite", nullable = false)
    private Date fecha_limite;

    @Column(name = "valor", nullable = false)
    private double valor;


}
