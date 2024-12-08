package com.back_hernansoft.back_hernansoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.back_hernansoft.back_hernansoft.entity.producto;

import java.util.List;

@Repository
public interface productoRepository extends JpaRepository<producto, Long> {

//    // Buscar productos por categoría
//    List<producto> findByCategoria(String categoria);
//
//    // Buscar productos cuyo nombre contenga un texto específico
//    List<producto> findByNombreContaining(String partialName);
//
//    // Buscar productos dentro de un rango de precios
//    List<producto> findByPrecioBetween(Double minPrecio, Double maxPrecio);
}
