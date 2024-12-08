package com.back_hernansoft.back_hernansoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.back_hernansoft.back_hernansoft.entity.archivo;

import java.util.List;

@Repository
public interface archivoRepository extends JpaRepository<archivo, Integer> {

//    // Buscar archivos por solicitud específica
//    List<archivo> findBySolicitudId(Integer solicitudId);
//
//    // Buscar archivos por ruta parcial
//    List<archivo> findByRutaArchivoContaining(String partialRutaArchivo);
}
