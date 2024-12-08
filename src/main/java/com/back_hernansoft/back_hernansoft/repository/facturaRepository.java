package com.back_hernansoft.back_hernansoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.back_hernansoft.back_hernansoft.entity.factura;

import java.sql.Date;
import java.util.List;

@Repository
public interface facturaRepository extends JpaRepository<factura, Integer> {

//    // Buscar facturas por fecha
//    List<factura> findByFecha(Date fecha);
//
//    // Buscar facturas por cliente
//    List<factura> findByClienteId(Integer clienteId);
}
