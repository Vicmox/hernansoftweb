package com.back_hernansoft.back_hernansoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.back_hernansoft.back_hernansoft.entity.mensaje;

import java.util.List;

@Repository
public interface mensajeRepository extends JpaRepository<mensaje, Integer> {

//    // Buscar mensajes por remitente
//    List<mensaje> findByRemitenteId(Integer remitenteId);
//
//    // Buscar mensajes por destinatario
//    List<mensaje> findByDestinatarioId(Integer destinatarioId);
//
//    // Buscar mensajes por asunto
//    List<mensaje> findByAsuntoContaining(String asunto);
    // Buscar mensajes enviados y recibidos por id
    @Query("SELECT m FROM mensaje m WHERE m.idEmisor = :emisorId OR m.idDestinatario = :destinatarioId")
    List<mensaje> findByEmisorIdUsuarioOrDestinatarioIdUsuario(Integer emisorId, Integer destinatarioId);
    
}
