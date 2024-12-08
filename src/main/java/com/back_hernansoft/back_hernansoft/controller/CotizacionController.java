package com.back_hernansoft.back_hernansoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_hernansoft.back_hernansoft.entity.cotizacion;
import com.back_hernansoft.back_hernansoft.service.CotizacionService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping()
public class CotizacionController {
    
    @Autowired
    private CotizacionService cotizacionService;

    @PostMapping("/cotizaciones")
    public ResponseEntity<cotizacion> registrarCotizacion(@RequestBody cotizacion cotizacion) {
        cotizacion nuevaCotizacion = cotizacionService.registrarCotizacion(cotizacion);
        return ResponseEntity.ok(nuevaCotizacion);
    }

    @GetMapping("/cotizaciones")
    public ResponseEntity<List<cotizacion>> obtenerCotizaciones() {
        List<cotizacion> cotizaciones = cotizacionService.obtenerCotizaciones();
        return ResponseEntity.ok(cotizaciones);
    }
    
}
