package com.back_hernansoft.back_hernansoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.back_hernansoft.back_hernansoft.entity.metodopago;

import java.util.List;

@Repository
public interface metodopagoRepository extends JpaRepository<metodopago, Integer> {

//    // Buscar métodos de pago por tipo
//    List<metodopago> findByTipo(String tipo);
}
