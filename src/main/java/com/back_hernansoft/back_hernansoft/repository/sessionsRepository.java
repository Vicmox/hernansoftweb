package com.back_hernansoft.back_hernansoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.back_hernansoft.back_hernansoft.entity.sessions;

import java.util.Optional;

import java.util.List;

@Repository
public interface sessionsRepository extends JpaRepository<sessions, Integer> {

//    // Buscar sesión por usuario
//    Optional<sessions> findByUsuarioId(Integer usuarioId);
//
//    // Buscar sesión activa
//    List<sessions> findByEstado(String estado);
}
