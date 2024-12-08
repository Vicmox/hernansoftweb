package com.back_hernansoft.back_hernansoft.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import com.back_hernansoft.back_hernansoft.entity.usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface usuarioRepository extends JpaRepository<usuario, Integer> {

    public Optional<usuario> findByIdentificacion(String identificacion);
    boolean existsByIdentificacion(String identificacion);
    boolean existsByCorreo(String correo);
    List<usuario> findByRol(String rol);
    Optional<usuario> findIdByIdentificacion(String identificacion);
    Optional<usuario> findByNombre(String nombre);  
}
