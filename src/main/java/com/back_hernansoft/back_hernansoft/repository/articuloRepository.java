package com.back_hernansoft.back_hernansoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back_hernansoft.back_hernansoft.entity.articulo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.back_hernansoft.back_hernansoft.entity.ArticuloPedido;

@Repository
public interface articuloRepository extends JpaRepository<articulo, Integer> {

    
    @Query("SELECT ap FROM ArticuloPedido ap WHERE " +
           "(:estado IS NULL OR ap.estado = :estado) AND " +
           "(:cliente IS NULL OR ap.cliente.nombre LIKE %:cliente%) AND " +
           "(:marca IS NULL OR ap.articulo.marca LIKE %:marca%) AND " +
           "(:tipo IS NULL OR ap.articulo.tipo LIKE %:tipo%)")
    List<ArticuloPedido> findPedidosByFilters(@Param("estado") String estado,
                                              @Param("cliente") String cliente,
                                              @Param("marca") String marca,
                                              @Param("tipo") String tipo);
}
