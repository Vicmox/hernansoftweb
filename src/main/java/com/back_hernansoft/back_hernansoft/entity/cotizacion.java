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

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "cotizacion")
public class cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cotizacion")
    private Integer idCotizacion;

    @Column(name = "precio_total", nullable = false)
    private double precio_total;

    @Column(name = "observacion", nullable = false, length = 500)
    private String observacion;

    @Column(name = "pedido_id", nullable = false)
    private Integer pedido_id;

    @Column(name = "estado_id", nullable = false)
    private Integer estado_id;

    @Column(name = "estado", nullable = false)
    private String estado;


}
