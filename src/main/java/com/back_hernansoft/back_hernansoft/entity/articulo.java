package com.back_hernansoft.back_hernansoft.entity;
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

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "articulo")
public class articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_articulo")
    private Integer id_articulo;

    public Integer getId_articulo() {
    	return this.id_articulo;
    }
    public void setId_articulo(Integer id_articulo) {
    	this.id_articulo = id_articulo;
    }


    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    public String getDescripcion() {
    	return this.descripcion;
    }
    public void setDescripcion(String descripcion) {
    	this.descripcion = descripcion;
    }


    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticuloPedido> articulosPedidos = new ArrayList<>();


}
