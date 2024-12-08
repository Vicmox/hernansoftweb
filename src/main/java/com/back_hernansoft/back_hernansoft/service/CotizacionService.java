package com.back_hernansoft.back_hernansoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.back_hernansoft.back_hernansoft.entity.cotizacion;
import com.back_hernansoft.back_hernansoft.repository.cotizacionRepository;

@Service
public class CotizacionService {
    @Autowired
    private cotizacionRepository cotizacionRepository;

    public cotizacion registrarCotizacion(cotizacion cotizacion) {
        return cotizacionRepository.save(cotizacion);
    }
    public List<cotizacion> obtenerCotizaciones() {
        return cotizacionRepository.findAll();
    }
}
