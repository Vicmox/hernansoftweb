package com.back_hernansoft.back_hernansoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.back_hernansoft.back_hernansoft.entity.cotizacion;

import java.util.List;

@Repository
public interface cotizacionRepository extends JpaRepository<cotizacion, Integer> {

//    // Buscar cotizaciones por cliente
//    List<cotizacion> findByClienteId(Integer clienteId);
//
//    // Buscar cotizaciones por estado
//    List<cotizacion> findByEstado(String estado);
//
//    // Buscar cotizaciones con valor dentro de un rango
//    List<cotizacion> findByValorTotalBetween(Double minValor, Double maxValor);
}
