package com.back_hernansoft.back_hernansoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.back_hernansoft.back_hernansoft.entity.pedido;

import java.sql.Date;
import java.util.List;

@Repository
public interface pedidoRepository extends JpaRepository<pedido, Integer> {

//    // Buscar pedidos por cliente
//    List<pedido> findByClienteId(Integer clienteId);
//
//    // Buscar pedidos por estado
//    List<pedido> findByEstado(String estado);
//
//    // Buscar pedidos por fecha de creación
//    List<pedido> findByFechaCreacion(Date fechaCreacion);
}
