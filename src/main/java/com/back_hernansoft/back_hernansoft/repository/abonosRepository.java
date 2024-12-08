package com.back_hernansoft.back_hernansoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.back_hernansoft.back_hernansoft.entity.abonos;

import java.sql.Date;
import java.util.List;

@Repository
public interface abonosRepository extends JpaRepository<abonos, Integer> {

//    // Buscar todos los abonos de un pedido específico
//    List<abonos> findByPedidoId(Integer pedidoId);
//    List<abonos> findByPedido_Id(Integer pedidoId);
//
//    // Buscar abonos dentro de un rango de valores
//    List<abonos> findByValorBetween(Double minValor, Double maxValor);
//
//    // Buscar abonos por fecha
//    List<abonos> findByFecha(Date fecha);
}
