package com.back_hernansoft.back_hernansoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.back_hernansoft.back_hernansoft.entity.solicitud_cotizacion;

import java.util.List;

@Repository
public interface solicitudCotizacionRepository extends JpaRepository<solicitud_cotizacion, Integer> {

//    // Buscar solicitudes por cliente
//    List<solicitud_cotizacion> findByClienteId(Integer clienteId);
//
//    // Buscar solicitudes por estado
//    List<solicitud_cotizacion> findByEstado(String estado);
}
